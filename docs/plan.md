# Cobudget
Control your budget

# Wymagania

## Konfiguracja
- [ ] logowanie użytkownika przez maila i oauth2
- [ ] wprowadzenie kategorii wydatków (głównych i podkategorii)
- [ ] wprowadzenie aktualnego stanu kont, tak aby monitorować ogólny zasób pieniędzy
- [ ] wprowadzenie wyboru waluty
- [ ] wybranie godziny powiadomień

## Budżet
- [ ] zaplanowanie budżetu na dany miesiąc
- [ ] powiadomienie, gdy nie stworzy budżetu na dany miesiąć (1 i 7 dnia miesiąca)
- [ ] wybranie, że dany miesiąc będzie nie zaplanowany
- [ ] wprowadzenie aktualnego stanu kont na koniec każdego miesiąca z możliwością automatycznego wyliczenia na podstawie wydatków z danego miesiąca
- [ ] powiadomienie o wprowadzenie stanu kont na koniec miesiąca

## Wydatki
- [ ] dodawanie nowego wydatku
- [ ] powiadomienie o wypełnienie wydatków z danego dnia
- [ ] możliwość zeskanowania paragonu i dodania wydatków z niego z możliwością edycji
- [ ] zeskanowanie sklepu z paragonu i automatyczne wybranie kategorii na jego podstawie (ostatnio użyta)
- [ ] Wbudowany kalkulator
- [ ] Zanotować nowy wydatek i dodać przypomnienie na późniejsze wypełnienie (widok do wypełnienia)

## Monitoring
- wyświetlanie danych historycznych po wybraniu danego roku

### Na każdy miesiąc
- [ ] widok pokazujący dane za zeszłe 12 miesięcy
- [ ] Tworzenie wykresów:
    - [ ] ile wydano vs ile zaplanowana
    - [ ] procent wydatków na każdą z głównych kategorii
    - [ ] wypełnienie budżetu w każdej z głównych kategorii
- [ ] ile mogę jeszcze wydać do końca miesiąca
- [ ] ile już wydałem procentowo z przychodów
- [ ] jak już zrealizowałem plany na główną kategorię
- [ ] suma przychodów i wydatków na daną kategorię i podkategorię
- [ ] suma zaplanowanych przychodów i wydatków na daną kategorię i podkategorię

### Roczne
- [ ] Dane:
    - [ ] ile wydano vs ile zaplanowano
    - [ ] wydatki na każdą kategorię
    - [ ] ile już wydałem procentowo z przychodów
    - [ ] jak już zrealizowałem plany na główną kategorię
    - [ ] podsumowanie średnich na miesiąc zaplanowanych wydatków i przychodów w każdej z kategorii i podkategorii
    - [ ] podsumowanie średnich na miesiąc wydatków na każdą z kategorii i podkategorii

### Ogólnie
- [ ] Dane:
    - [ ] podsumowanie średnich rocznych zaplanowanych wydatków w każdej z kategorii i podkategorii
    - [ ] podsumowanie średnich rocznych wydatków na każdą z kategorii i podkategorii
    - [ ] podsumowanie średnich w danym miesiącu zaplanowanych wydatków w każdej z kategorii i podkategorii (ile średnio wydaje w styczniu każdego roku)
    - [ ] podsumowanie średnich w danym miesiącu wydatków na każdą z kategorii i podkategorii

# Monetyzacja

## Wersja darmowa
- [ ] brak możliwości zobaczenia danych z zeszłych lat (nie usuwamy ich tylko blokujemy)
- [ ] brak możliwości skanowania paragonów
- [ ] brak ogólnego stanu kont do monitorowania
- [ ] wybranie godziny powiadomień
- [ ] monitoring jedynie w postaci miesięcznej:
    - [ ] widok danych za ostatnie 12 miesięcy
    - [ ] ile mogę wydać do końca miesiąca i procentowo
    - [ ] sumy przychodów i wydatków jedynie na główne kategorie
    - [ ] sumy zaplanowanych przychodów i wydatków jedynie na główne kategorie

## Wersja pro
- [ ] opłata abonamentowa (na miesiąc, trzy lub rok)
- [ ] otrzymanie za darmo na rok po przesłaniu ficzera, który zostanie zaakceptowany
- [ ] odblokowanie możliwości obejrzenia danych historycznych
- [ ] odblokowanie wszystkich ficzerów


# Marketing
Reklamy w aplikacji:
    - [ ] po pierwszym miesiącu możliwość wypróbowania konta pro na miesiąc
    - [ ] po roku przypomnienie o usunięciu danych sprzed roku
    - [ ] informacja, że za przesłanie ficzera dostaje konto pro na rok za darmo

# Technologie
- pwa
- frontend - react?
- backend - spring
- baza danych: mongodb lub mysql
- wersja mobilna zorientowana na dodawanie wydatków i przypomnienia
- wersja desktopowa zorientowana na zarządzanie i analizę

# Do zrobienia
- [X] Zaplanować monetyzację
- [X] Dowiedzieć się jak robić PWA app
- [ ] Nauczyć się reacta
- [ ] Rozważyć zastosowanie frameworka do css
- [ ] Sprawdzić czy lepsza będzie relacyjna czy nierelacyjna baza danych
- [ ] Nauczyć się wysyłać powiadomienia z PWA
- [ ] Zaplanować ficzery w pierwszej wersji aplikacji
- [ ] Zaprojektować UI
- [ ] Wybrać paletę kolorów
- [ ] Stworzyć repozytorium na github (wybrać czy dwa oddzielne dla frontu i backendu czy jedno)
- [X] Wybrać sposób hostowania
- [ ] Rozważyć za i przeciw zastosowania bazy MongoDB
