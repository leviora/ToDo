document.addEventListener("DOMContentLoaded", function () {
    const chartCanvas = document.getElementById("completedTasksChart");
    if (chartCanvas) {
        fetch("/api/statystyki/zadania")
            .then(response => response.json())
            .then(data => {
                const ctx = chartCanvas.getContext("2d");
                new Chart(ctx, {
                    type: 'doughnut',
                    data: {
                        labels: ['Zakończone', 'Nieukończone'],
                        datasets: [{
                            data: [data.completed, data.uncompleted],
                            backgroundColor: ['#198754', '#dc3545'],
                            borderWidth: 1
                        }]
                    },
                    options: {
                        responsive: true,
                        plugins: {
                            legend: {
                                position: 'bottom'
                            }
                        }
                    }
                });
            })
            .catch(error => console.error("Błąd wczytywania danych wykresu:", error));
    }
});
