# TP-04.2A-Room-LiveData-et-ViewModel


---

# RoomWordsSample - Application Overview

## Application Structure

### 1. Displaying Words in a List
- The app features a functional display of words in a list using:
  - `MainActivity`
  - `RecyclerView`
  - `WordListAdapter`

### 2. Adding Words to the List
- Ability to add new words to the displayed list is facilitated through:
  - `NewWordActivity`

### 3. Word Entity Class
- Words are instances of the `Word` entity class.

### 4. Caching Words in WordListAdapter
- `WordListAdapter` caches words as a list (`mWords`), which is automatically updated and re-displayed when data changes.

### 5. Automatic Updates using Observers
- Automatic updates occur due to an Observer in `MainActivity`.
- The Observer monitors words and triggers `onChange()` upon any changes, updating `mWords` in the `WordListAdapter`.

### 6. LiveData for Observing Data Changes
- LiveData enables data observation.
- Observed LiveData is `LiveData<List<Word>>`, returned by the `WordViewModel` object.

### 7. WordViewModel Functionality
- `WordViewModel` abstracts backend details from the UI.
- It provides methods to access UI data and returns LiveData, facilitating MainActivity's observer relationship.
- Views, activities, and fragments interact solely with data via the ViewModel, independent of its data source.

### 8. Repository Handling Data Sources
- The data source is managed by the Repository.
- The ViewModel interacts with the Repository without knowing the specific Repository involved.
- The Repository provides methods exposed by the Repository interface.

### 9. Room as Backend Database
- The Repository manages one or more data sources, with Room functioning as the backend database in this application.
- Room, built around an SQLite database, simplifies database operations by automating tasks previously handled manually.
- The DAO (Data Access Object) maps method calls to database queries, such as `getAllWords()`, where Room executes `SELECT * from word_table ORDER BY word ASC`.
- The query result is LiveData observed, triggering `onChanged()` in the Observer interface upon Room data changes, updating the UI.

---

