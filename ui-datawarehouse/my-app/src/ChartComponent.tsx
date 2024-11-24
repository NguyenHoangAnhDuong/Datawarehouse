import React, { useEffect } from "react";
import Highcharts from "highcharts";
import moment from 'moment'; // Sử dụng thư viện moment để xử lý thời gian

interface ChartComponentProps {
    data: { time: string; buy: number; sell: number }[];
    period: string;
}

const ChartComponent: React.FC<ChartComponentProps> = ({ data, period }) => {
    useEffect(() => {
        if (data.length === 0) {
            console.log("null");
            return;
        }

        console.log("Received data:", data);


        const formattedData = data.map(item => [new Date(item.time).getTime(), item.buy]);
        const formattedSellData = data.map(item => [new Date(item.time).getTime(), item.sell]);

        console.log("Formatted buy data:", formattedData);
        console.log("Formatted sell data:", formattedSellData);

        // Options cho biểu đồ Highcharts
        const options: Highcharts.Options = {
            chart: {
                renderTo: "chart-container",
                type: "line",
                backgroundColor: "white",
                plotBorderWidth: 1,
            },
            title: {
                text: "SJC (nghìn/lượng): 85,000/87,000",
                style: {
                    fontSize: "20px",
                    fontWeight: "bold",
                },
            },
            subtitle: {
                text: "Cập nhật lúc 08:36 23/11/2024",
                style: {
                    fontSize: "15px",
                    fontWeight: "italic",
                },
            },
            xAxis: {
                type: "datetime",
                labels: {
                    format: period === "24h" ? "{value:%H:%M}" : "{value:%d/%m}",
                },
                tickInterval: (() => {
                    let interval = 0;

                    if (period === "24h") {
                        interval = 24 * 60 * 60 * 1000; // Tổng thời gian trong 24h (milliseconds)
                        return interval /24; // Chia thành 6 khoảng
                    } else if (period === "3d") {
                        interval = 3 * 24 * 60 * 60 * 1000; // Tổng thời gian trong 3 ngày
                        return interval / 10; // Chia thành 6 khoảng
                    } else if (period === "1w") {
                        interval = 7 * 24 * 60 * 60 * 1000; // Tổng thời gian trong 7 ngày
                        return interval / 6; // Chia thành 6 khoảng
                    }

                    return interval; // Mặc định nếu không khớp với các khoảng thời gian trên
                })(),
                title: {
                    text: null,
                },
            },


            yAxis: {
                title: {
                    text: null,
                },
                min: 84000,
                max: 88000,
                tickInterval: 500,
                labels: {
                    formatter: function () {
                        return Highcharts.numberFormat(this.value as number, 0);
                    },
                },
            },
            tooltip: {
                formatter: function () {
                    const date = Highcharts.dateFormat('%d/%m %H:%M', this.x as number);
                    return `<b>${this.series.name}</b><br/>${date}<br/>${Highcharts.numberFormat(this.y as number, 0)} (nghìn/lượng)`;
                },
            },

            legend: {
                layout: "horizontal",
                align: "center",
                verticalAlign: "top",
            },
            series: [
                {
                    type: "line",
                    name: "Mua",
                    color: "#FF0000",
                    data: formattedData,
                },
                {
                    type: "line",
                    name: "Bán",
                    color: "#00a2ff",
                    data: formattedSellData,
                },
            ],
            credits: {
                enabled: false,
            },
        };

        Highcharts.chart("chart-container", options);
    }, [data, period]);

    return <div id="chart-container" style={{ width: "100%", height: "400px" }}></div>;
};

export default ChartComponent;
