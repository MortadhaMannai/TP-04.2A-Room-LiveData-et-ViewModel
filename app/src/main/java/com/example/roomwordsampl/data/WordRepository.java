package com.example.roomwordsampl.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.roomwordsampl.data.db.WordDao;
import com.example.roomwordsampl.data.db.WordRoomDatabase;
import com.example.roomwordsampl.model.Word;

import java.util.List;

public class WordRepository {

    private final WordDao mWordDao;
    private final LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    // Room exécute toutes les requêtes sur un thread distinct.
    // Les données LiveData observées avertiront l'observateur lorsque les données auront changé.
    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    // Vous devez appeler cela sur un thread non-UI ou votre application lancera une exception.
    // Room garantit que vous n'effectuez aucune opération longue sur le thread principal, bloquant l'interface utilisateur.
    public void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> mWordDao.insert(word));
    }
}
