// convertToXlsx.js
const fs = require('fs');
const XLSX = require('xlsx');

const csvFilePath = process.argv[2]; // Nhận tên file CSV từ đối số dòng lệnh
const xlsxFilePath = process.argv[3]; // Nhận tên file XLSX từ đối số dòng lệnh

// Đọc file CSV
const csvData = fs.readFileSync(csvFilePath, 'utf8');

// Chuyển đổi CSV thành worksheet
// Tách dữ liệu bằng cách sử dụng dấu hai chấm và dấu gạch ngang
const worksheet = XLSX.utils.aoa_to_sheet(csvData.split('\n').map(row => {
    return row.split(',').flatMap(item => item.split('_').map(subItem => subItem.trim()));
}));

// Tạo workbook và thêm worksheet vào
const workbook = XLSX.utils.book_new();
XLSX.utils.book_append_sheet(workbook, worksheet, 'Sheet1');

// Ghi file XLSX
XLSX.writeFile(workbook, xlsxFilePath);
console.log(`Converted ${csvFilePath} to ${xlsxFilePath} successfully`);
