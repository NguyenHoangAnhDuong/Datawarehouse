const axios = require('axios');
const cheerio = require('cheerio');
const https = require('https');
const fs = require('fs');
const { parse } = require('json2csv');
const { exec } = require('child_process');

async function fetchGoldTables() {
    try {
        const dateNow = new Date();
        const formatDate = `${dateNow.getDate()}-${dateNow.getMonth() + 1}-${dateNow.getFullYear()}`
        console.log(`Lấy dữ liệu vào ${formatDate}`);

        const url = 'https://doji.vn/bang-gia-vang/';
        const { data } = await axios.get(url, {
            httpsAgent: new https.Agent({
                rejectUnauthorized: false // Bỏ qua xác minh SSL
            })
        });
        const $ = cheerio.load(data);

        const records = [];
        let idCounter = 1; // Biến đếm id cho từng hàng

        $('div._table').each((_, table) => {
            const nameBrach = $(table).find('div._title-tax').first().text().replace(/giá vàng trang sức tại|giá vàng/, '').trim();
            const goldTypes = [];
            $(table).find('div._taxonomy ._block').each((_, element) => {
                const type = $(element).text().trim();
                goldTypes.push(type);
            });

            const buyPrices = [];
            $(table).find('div._buy ._block').each((_, element) => {
                const price = $(element).text().trim() == '-' ? ' ' : $(element).text().trim() + ',000';
                buyPrices.push(price);
            });

            const sellPrices = [];
            $(table).find('div._Sell ._block').each((_, element) => {
                const price = $(element).text().trim() == '-' ? ' ' : $(element).text().trim() + ',000';
                sellPrices.push(price);
            });

            goldTypes.forEach((type, i) => {
                if (type) {
                    records.push({
                        id: idCounter++,
                        goldTypes: type,
                        nameBrach,
                        buy: buyPrices[i] || 'N/A',
                        sell: sellPrices[i] || 'N/A'
                    });
                }
            });
        });

        const csvFilePath = `D:\\Datawarehouse\\FileDataCSV\\gold_prices_doji_${formatDate}.csv`;
        const csvRecords = records.map(record => ({
            Id: record.id,
            TypeName: record.goldTypes,
            Company: record.nameBrach,
            BuyValue: record.buy,
            SellValue: record.sell
        }));
        const csv = parse(csvRecords, { fields: ['Id', 'TypeName', 'Company', 'BuyValue', 'SellValue'] });
        fs.writeFileSync(csvFilePath, csv);

        const xlsxFilePath = `D:\\Datawarehouse\\FileDataXLSX\\gold_prices_doji_${formatDate}.xlsx`;
        const methodConvert = 'D:\\Datawarehouse\\convertCSVtoXLSX.js'
        exec(`node ${methodConvert} ${csvFilePath} ${xlsxFilePath}`, (error, stdout, stderr) => {
            if (error) {
                console.error(`Error converting to XLSX: ${error.message}`);
                return;
            }
            if (stderr) {
                console.error(`stderr: ${stderr}`);
                return;
            }
            console.log(stdout);
        });

        console.log('Dữ liệu đã được ghi vào file goldPrices.csv');
    } catch (error) {
        console.error('Có lỗi xảy ra:', error);
    }
}

fetchGoldTables();
