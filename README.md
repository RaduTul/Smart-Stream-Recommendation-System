# Smart Stream Recommendation System

Acest proiect implementează un sistem de recomandări pentru o platformă de streaming care gestionează muzică, podcast-uri și cărți audio. Sistemul administrează utilizatori, creatori de conținut și stream-uri de conținut. Proiectul pune in aplicare Design Patterns si ajuta la intelegerea acestora.

## Prezentare generală a proiectului

Aplicația simulează o platformă de streaming cu următoarele funcționalități principale:
- Gestionarea creatorilor de conținut (muzicieni, gazde de podcast, autori de cărți audio)
- Gestionarea stream-urilor (melodii, podcast-uri, cărți audio)
- Urmărirea istoricului de ascultare al utilizatorilor
- Recomandări de stream-uri bazate pe streamerii ascultați anterior

## Arhitectură și Design Patterns

Proiectul urmează principiile programării orientate pe obiecte și încorporează următoarele design patterns:

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
Clasa `Factory` implementează pattern-ul Factory pentru a crea diferite tipuri de obiecte muzicale (Streamer, Streams, User) în funcție de tipul specificat. Acest pattern centralizează crearea obiectelor.

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
Clasa abstractă `Command` definește un contract pentru operațiile care pot fi executate în sistem. Clasa `Execute` extinde această clasă abstractă și oferă implementări concrete. Acest pattern încapsulează cererile ca obiecte.

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

### 4. Moștenire
Proiectul utilizează moștenirea, cu o clasă de bază `Music` care este extinsă de clasele `Streamer`, `Streams` și `User`. Clasa `Music` servește ca tip de bază comun pentru ierarhia de obiecte muzicale.

## Funcționalitate implementată

### Gestionarea Streaming-ului
- **ADD**: Adaugă noi stream-uri pe platformă
- **LIST**: Listează stream-urile asociate unui streamer sau utilizator
- **DELETE**: Elimină stream-uri de pe platformă

### Interacțiunea utilizatorilor
- **LISTEN**: Urmărește când utilizatorii ascultă stream-uri și actualizează contoarele de ascultări
- **RECOMMEND**: Recomandă stream-uri bazate pe streamerii ascultați anterior de utilizator

## Algoritm de recomandare

### Recomandări standard (RECOMMEND)
Algoritmul de recomandare implementat în cod:
1. Identifică streamerii ascultați de utilizator
2. Colectează stream-urile neascultat de utilizator de la acești streameri
3. Sortează aceste stream-uri după numărul de ascultări 
4. Prezintă top 5 stream-uri

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
- Duratele stream-urilor sunt afișate în format HH:MM:SS (sau MM:SS dacă durata este mai mică de o oră) prin metoda `displayTime`
- Datele sunt formatate ca DD-MM-YYYY prin metoda `formatDate` care convertește timestamp-urile Unix

## Format de ieșire

Sistemul generează date formatate JSON pentru comenzile LIST și de recomandare, urmând schema definită în specificațiile proiectului.
