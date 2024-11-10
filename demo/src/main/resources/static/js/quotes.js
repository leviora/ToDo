
const quotes = [
    { text: "Nie czekaj. Czas nigdy nie będzie odpowiedni.", author: "Napoleon Hill" },
    { text: "Twoje życie staje się lepsze tylko, gdy Ty stajesz się lepszym.", author: "Brian Tracy" },
    { text: "Każdy sukces zaczyna się od decyzji o próbie.", author: "John C. Maxwell" },
    { text: "Przyszłość należy do tych, którzy wierzą w piękno swoich marzeń.", author: "Eleanor Roosevelt" },
    { text: "Nigdy nie rezygnuj z czegoś, czego pragniesz. Tylko dlatego, że nie udało się za pierwszym razem.", author: "Albert Einstein" },
    { text: "Działaj tak, jakby nie było żadnych ograniczeń.", author: "Les Brown" },
    { text: "Jeśli potrafisz o czymś marzyć, to potrafisz także tego dokonać.", author: "Walt Disney" },
    { text: "Sukces nie przychodzi do ciebie, to ty musisz do niego iść.", author: "Marva Collins" },
    { text: "Największym ryzykiem jest nie podejmować żadnego ryzyka.", author: "Mark Zuckerberg" },
    { text: "Marzenia się nie spełniają. Marzenia się spełnia.", author: "Anonim" },
    { text: "Zawsze wydaje się niemożliwe, dopóki nie zostanie zrobione.", author: "Nelson Mandela" }
];


// Zmienna do śledzenia aktualnego cytatu
let currentQuoteIndex = 0;

// Funkcja do wyświetlania cytatów z animacją
function displayQuote() {
    const quoteElement = document.getElementById("quote");
    const authorElement = document.getElementById("author");

    // Usuwamy widoczność cytatu i autora
    quoteElement.classList.remove("visible");
    authorElement.classList.remove("visible");

    // Po zakończeniu animacji zmieniamy tekst
    setTimeout(() => {
        const quote = quotes[currentQuoteIndex];
        quoteElement.textContent = `"${quote.text}"`;
        authorElement.textContent = `- ${quote.author}`;

        // Dodajemy animację pojawiania się nowego cytatu
        quoteElement.classList.add("visible");
        authorElement.classList.add("visible");

        // Przechodzimy do kolejnego cytatu
        currentQuoteIndex = (currentQuoteIndex + 1) % quotes.length;
    }, 1000); // Czekamy na zakończenie animacji zanikania

}

// Wyświetlanie cytatu co 7 sekund
setInterval(displayQuote, 7000);

// Wyświetl pierwszy cytat od razu
displayQuote();
