const axios = require('axios');
const fs = require('fs');
const { parse } = require('json2csv');
const { exec } = require('child_process');

const https = require('https');

const httpsAgent = new https.Agent({
    rejectUnauthorized: false,
});

function formatData(data) {
    return data.map(item => {
        for (let key in item) {
            if (item[key] === null || item[key] === undefined) {
                item[key] = "No_Value";
            }
        }
        return item;
    });
}

async function fetchAndSaveGoldPrices() {
    try {

        //Get Date
        const dateNow = new Date();
        const formatDate = `${dateNow.getDate()}-${dateNow.getMonth() + 1}-${dateNow.getFullYear()}`
        console.log(`Lấy dữ liệu vào ${formatDate}`);

        //Scrape Data by URL
        const url = 'https://sjc.com.vn/GoldPrice/Services/PriceService.ashx';
        const response = await axios.get(url, { httpsAgent });
        const data = formatData(response.data.data);

        //Save data to CSV file
        const fields = Object.keys(data[0]);
        const csv = parse(data, { fields });
        const csvFilePath = `D:\\Datawarehouse\\FileDataCSV\\gold_prices_sjc_${formatDate}.csv`;
        fs.writeFileSync(csvFilePath, csv);
        console.log(`Dữ liệu đã được lưu vào ${csvFilePath}`);

        //Save data to XLSX file
        const xlsxFilePath = `D:\\Datawarehouse\\FileDataXLSX\\gold_prices_sjc_${formatDate}.xlsx`;
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
        console.error('Lỗi khi lấy hoặc lưu dữ liệu:', error);
    }
}

fetchAndSaveGoldPrices();
