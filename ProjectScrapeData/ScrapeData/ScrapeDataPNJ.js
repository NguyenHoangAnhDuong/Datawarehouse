const axios = require('axios');
const cheerio = require('cheerio');
const fs = require('fs');
const createCsvWriter = require('csv-writer').createObjectCsvWriter;
const { exec } = require('child_process');

const https = require('https');
const httpsAgent = new https.Agent({
    rejectUnauthorized: false,
});

async function scrapeGoldPrices() {
    try {

        const dateNow = new Date();
        const formatDate = `${dateNow.getDate()}-${dateNow.getMonth()+1}-${dateNow.getFullYear()}`
        console.log(`Lấy dữ liệu vào ${formatDate}`);

        const { data } = await axios.get('https://bieudogiavang.vn/gia-vang-pnj', httpsAgent);
        const $ = cheerio.load(data);

        const goldPrices = [];
        const rows = $('.min-w-full tbody tr');

        rows.each((index, row) => {
            const columns = $(row).find('td');
            const item = {
                rank: $(columns[0]).text().trim(),
                type: $(columns[1]).find('span').first().text().trim(), // Lấy giá trị từ span đầu tiên
                company: $(columns[2]).text().trim(),
                buyPrice: $(columns[3]).find('span').first().text().trim().replace(/\./g, '').replace(/\n/g, '').trim(), // Lấy giá trị từ span đầu tiên
                sellPrice: $(columns[4]).find('span').first().text().trim().replace(/\./g, '').replace(/\n/g, '').trim(), // Lấy giá trị từ span đầu tiên
            };
            goldPrices.push(item);
        });

        // Định nghĩa cấu trúc CSV
        const csvFilePath = `D:\\Datawarehouse\\FileDataCSV\\gold_prices_pnj_${formatDate}.csv`;
        const csvWriter = createCsvWriter({
            path: csvFilePath, // Tên tệp CSV
            header: [
                { id: 'rank', title: 'Id' },
                { id: 'type', title: 'TypeName' },
                { id: 'company', title: 'Company' },
                { id: 'buyPrice', title: 'BuyValue' },
                { id: 'sellPrice', title: 'SellValue' },
            ],
        });

        // Ghi dữ liệu vào tệp CSV
        await csvWriter.writeRecords(goldPrices);
        console.log(`Gold prices saved to ${csvFilePath}`);

        const xlsxFilePath = `D:\\Datawarehouse\\FileDataXLSX\\gold_prices_pnj_${formatDate}.xlsx`;
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
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}

scrapeGoldPrices();
