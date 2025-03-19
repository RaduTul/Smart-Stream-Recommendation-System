# Smart Stream Recommendation System

Acest proiect implementează un sistem inteligent de recomandări pentru o platformă de streaming care gestionează muzică, podcast-uri și cărți audio. Sistemul administrează utilizatori, creatori de conținut și conținutul de streaming, oferind funcții avansate de recomandare.

## Prezentare generală a proiectului

Aplicația simulează o platformă de streaming cu următoarele funcționalități principale:
- Gestionarea creatorilor de conținut (muzicieni, gazde de podcast, autori de cărți audio)
- Gestionarea stream-urilor (melodii, podcast-uri, cărți audio)
- Urmărirea istoricului de ascultare al utilizatorilor
- Recomandări de stream-uri bazate pe preferințele utilizatorilor
- Recomandări surpriză bazate pe adăugări recente

## Arhitectură și Design Patterns

Proiectul urmează principiile programării orientate pe obiecte și încorporează mai multe design patterns:

### 1. Singleton Pattern
Pattern-ul Singleton este utilizat pentru a menține un depozit central de date prin intermediul clasei `Singleton`. Acesta asigură că există o singură instanță a magazinului de date care gestionează listele de streameri, stream-uri și utilizatori în întreaga aplicație.

```java
public static Singleton getInstance() {
    if (instance == null) {
        instance = new Singleton();
    }
    return instance;
}
```

### 2. Factory Pattern
Clasa `Factory` implementează pattern-ul Factory pentru a crea diferite tipuri de obiecte muzicale (Streamer, Streams, User) în funcție de tipul specificat. Acest pattern centralizează crearea obiectelor și permite extinderea ușoară pentru noi tipuri.

```java
public Music Create(String tip, String[] date) {
    if (tip.equals("Stream")) {
        // Creează obiect Stream
    } else if (tip.equals("Streamer")) {
        // Creează obiect Streamer
    } else if (tip.equals("User")) {
        // Creează obiect User
    }
    return null;
}
```

### 3. Command Pattern
Clasa abstractă `Command` definește un contract pentru operațiile care pot fi executate în sistem. Clasa `Execute` extinde această clasă abstractă și oferă implementări concrete. Acest pattern încapsulează cererile ca obiecte, permițând parametrizarea clienților cu diferite cereri.

```java
abstract class Command {
    public abstract void ADD(String[] data);
    public abstract void LIST(int data);
    public abstract void DELETE(String[] data);
    public abstract void LISTEN(String[] data);
    public abstract void RECOMMEND(String[] data);
    public abstract void SURPRISE(String[] data);
}
```

### 4. Moștenire și Template Method Pattern
Proiectul utilizează extensiv moștenirea, cu o clasă de bază `Music` care este extinsă de clasele `Streamer`, `Streams` și `User`. Această abordare stabilește un comportament comun, permițând în același timp implementări specializate.

## Funcționalitate

### Gestionarea Streaming-ului
- **ADD**: Adaugă noi stream-uri pe platformă
- **LIST**: Listează stream-urile asociate unui streamer sau utilizator
- **DELETE**: Elimină stream-uri de pe platformă

### Interacțiunea utilizatorilor
- **LISTEN**: Urmărește când utilizatorii ascultă stream-uri
- **RECOMMEND**: Recomandă stream-uri pe baza istoricului de ascultare al utilizatorului
- **SURPRISE**: Oferă recomandări surpriză cu conținut nou

## Algoritmi de recomandare

### Recomandări standard
Algoritmul de recomandare se bazează pe preferințele utilizatorului:
1. Din lista de streameri ascultați de utilizator, identifică top 5 stream-uri neascultatede cu cele mai multe ascultări
2. Recomandările sunt filtrate după tipul de stream solicitat (SONG, PODCAST sau AUDIOBOOK)

## Structura datelor

Aplicația utilizează trei clase principale pentru a reprezenta entitățile:
- **Streamer**: Reprezintă creatorii de conținut (muzicieni, gazde de podcast sau autori de cărți audio)
- **Streams**: Reprezintă conținutul efectiv (melodii, episoade de podcast sau cărți audio)
- **User**: Reprezintă utilizatorii platformei cu istoricul lor de ascultare

## Detalii de implementare

Sistemul procesează date din fișiere CSV:
- `streamers.csv`: Informații despre creatorii de conținut
- `streams.csv`: Informații despre conținutul stream-urilor
- `users.csv`: Informații despre utilizatori și istoricul lor de ascultare

Comenzile sunt procesate dintr-un fișier text (`commands.txt`) și urmează formate specifice pentru diferite operațiuni definite în cerințele proiectului.

## Gestionarea timpului și a datelor

Sistemul gestionează informațiile despre timp și dată în formate specifice:
- Duratele stream-urilor sunt afișate în format HH:MM:SS (sau MM:SS dacă durata este mai mică de o oră)
- Datele sunt formatate ca DD-MM-YYYY

## Format de ieșire

Sistemul generează date formatate JSON pentru comenzile LIST și de recomandare, urmând schema definită în specificațiile proiectului.
