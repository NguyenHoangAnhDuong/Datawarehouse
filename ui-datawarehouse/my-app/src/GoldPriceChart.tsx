import React, { useState, useEffect } from 'react';
import './GoldPriceChart.css';
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS
import ChartComponent from "./ChartComponent";

const GoldPriceChart: React.FC = () => {
    const [timePeriod, setTimePeriod] = useState('24h');
    const [chartData, setChartData] = useState<any>([]);

    // Tạo dữ liệu giả cho biểu đồ
    const generateFakeData = (period: string) => {
        const data = [];
        const currentTime = new Date().getTime();
        const intervalMapping: Record<string, number> = {
            '24h': 60 * 60 * 1000,    // Mỗi giờ
            '3d': 6 * 60 * 60 * 1000, // Mỗi 6 giờ
            '1w': 24*(7/10) * 60 * 60 * 1000 // Mỗi ngày
        };

        const interval = intervalMapping[period] || 0;
        for (let i = 0; i < 10; i++) {
            const time = new Date(currentTime - i * interval);
            data.push({
                time: time.toISOString(), // Lưu timestamp chuẩn
                buy: 85000 + Math.random() * 500,
                sell: 86000 + Math.random() * 500
            });
        }
        setChartData(data);
    };

    // Fetch dữ liệu khi trang tải
    useEffect(() => {
        generateFakeData('24h');
    }, []);

    // Fetch dữ liệu khi thay đổi thời gian
    useEffect(() => {
        generateFakeData(timePeriod);
    }, [timePeriod]);

    const handlePeriodChange = (period: string) => {
        if (timePeriod !== period) setTimePeriod(period);
    };


    return (
        <div className="gold-price-container container-fluid d-flex flex-column vh-100">
            {/* Header */}
            <header className="header d-flex justify-content-between align-items-center p-3 border-bottom">
                <div className="logo text-primary fw-bold">
                    <h3>DOJI</h3>
                </div>
                <nav className="nav d-flex gap-4">
                    <span className="nav-link text-dark">TRANG CHỦ</span>
                    <span className="nav-link text-dark">BIỂU ĐỒ</span>
                    <span className="nav-link text-dark">TIN TỨC</span>
                    <span className="nav-link text-dark">ĐỊA ĐIỂM</span>
                </nav>
            </header>
            <div className="chart-section flex-grow-1 d-flex flex-column">

                <div className="chart flex-grow-1 d-flex justify-content-center align-items-center">
                    <ChartComponent data={chartData} period={timePeriod}/>
                </div>

                <div className="chart-controls d-flex justify-content-center gap-2 py-3">
                    <button
                        className={`btn btn-outline-primary ${timePeriod === '24h' ? 'active' : ''}`}
                        onClick={() => handlePeriodChange('24h')}>
                        Biểu đồ 24h
                    </button>
                    <button
                        className={`btn btn-outline-primary ${timePeriod === '3d' ? 'active' : ''}`}
                        onClick={() => handlePeriodChange('3d')}>
                        3 ngày
                    </button>
                    <button
                        className={`btn btn-outline-primary ${timePeriod === '1w' ? 'active' : ''}`}
                        onClick={() => handlePeriodChange('1w')}>
                        1 tuần
                    </button>
                </div>
            </div>


            {/* Footer */}
            <footer className="footer text-center py-2 bg-light">
                <p className="text-muted">Trực tiếp từ: Easy-forex & TPBank</p>
            </footer>
        </div>
    );
};

export default GoldPriceChart;
