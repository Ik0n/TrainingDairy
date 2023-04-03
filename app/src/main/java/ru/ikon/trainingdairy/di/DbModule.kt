package ru.ikon.trainingdairy.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository
import ru.ikon.trainingdairy.domain.repository.room.DiaryEntryDao
import ru.ikon.trainingdairy.domain.repository.room.DiaryEntryDatabase
import ru.ikon.trainingdairy.domain.repository.room.RoomDiaryEntryRepositoryImpl
import ru.ikon.trainingdairy.ui.attempt.AttemptContract
import ru.ikon.trainingdairy.ui.attempt.AttemptPresenter
import ru.ikon.trainingdairy.ui.day.DayContract
import ru.ikon.trainingdairy.ui.day.DayPresenter
import ru.ikon.trainingdairy.ui.exercise.ExerciseContract
import ru.ikon.trainingdairy.ui.exercise.ExercisePresenter
import ru.ikon.trainingdairy.ui.exerciseattempts.ExerciseAttemptsContract
import ru.ikon.trainingdairy.ui.exerciseattempts.ExerciseAttemptsPresenter
import ru.ikon.trainingdairy.ui.history.HistoryContract
import ru.ikon.trainingdairy.ui.history.HistoryPresenter
import ru.ikon.trainingdairy.ui.measure.MeasureContract
import ru.ikon.trainingdairy.ui.measure.MeasurePresenter
import ru.ikon.trainingdairy.ui.month.MonthContract
import ru.ikon.trainingdairy.ui.month.MonthPresenter
import ru.ikon.trainingdairy.ui.note.NoteContract
import ru.ikon.trainingdairy.ui.note.NotePresenter
import ru.ikon.trainingdairy.ui.parameters.ParametersContract
import ru.ikon.trainingdairy.ui.parameters.ParametersPresenter
import ru.ikon.trainingdairy.ui.programslist.ProgramsListContract
import ru.ikon.trainingdairy.ui.programslist.ProgramsListPresenter
import ru.ikon.trainingdairy.ui.training.TrainingContract
import ru.ikon.trainingdairy.ui.training.TrainingPresenter
import ru.ikon.trainingdairy.ui.userparameters.UserParametersContract
import ru.ikon.trainingdairy.ui.userparameters.UserParametersPresenter
import ru.ikon.trainingdairy.utils.REPOSITORY
import javax.inject.Named

@Module
class DbModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideDiaryDb(context: Context): DiaryEntryDatabase =
        Room.databaseBuilder(
            context,
            DiaryEntryDatabase::class.java, "diary-db"
        )
            .allowMainThreadQueries()
            .build()

    @Provides
    fun provideDiaryDao(diaryDb: DiaryEntryDatabase) = diaryDb.diaryEntryDao()

    @Provides
    @Named(REPOSITORY)
    fun providesRepository(diaryDao: DiaryEntryDao): DiaryEntryRepository = RoomDiaryEntryRepositoryImpl(diaryDao)



//    @Provides
//    @Named(REPOSITORY)
//    fun providesRepository(): DiaryEntryRepository = DummyDiaryEntryRepositoryImpl()

    @Provides
    fun providesAttemptPresenter(@Named(REPOSITORY) repo: DiaryEntryRepository):
            AttemptContract.Presenter = AttemptPresenter(repo)

    @Provides
    fun providesDayPresenter(@Named(REPOSITORY) repo: DiaryEntryRepository):
            DayContract.Presenter = DayPresenter(repo)

    @Provides
    fun providesExercisePresenter(@Named(REPOSITORY) repo: DiaryEntryRepository):
            ExerciseContract.Presenter = ExercisePresenter(repo)

    @Provides
    fun providesExerciseAttemptsPresenter(@Named(REPOSITORY) repo: DiaryEntryRepository):
            ExerciseAttemptsContract.Presenter = ExerciseAttemptsPresenter(repo)

    @Provides
    fun providesHistoryPresenter(@Named(REPOSITORY) repo: DiaryEntryRepository):
            HistoryContract.Presenter = HistoryPresenter(repo)

    @Provides
    fun providesMeasurePresenter(@Named(REPOSITORY) repo: DiaryEntryRepository):
            MeasureContract.Presenter = MeasurePresenter(repo)

    @Provides
    fun providesMonthPresenter(@Named(REPOSITORY) repo: DiaryEntryRepository):
            MonthContract.Presenter = MonthPresenter(repo)

    @Provides
    fun providesNotePresenter(@Named(REPOSITORY) repo: DiaryEntryRepository):
            NoteContract.Presenter = NotePresenter(repo)

    @Provides
    fun providesParametersPresenter(@Named(REPOSITORY) repo: DiaryEntryRepository):
            ParametersContract.Presenter = ParametersPresenter(repo)

    @Provides
    fun providesProgramsListPresenter(@Named(REPOSITORY) repo: DiaryEntryRepository):
            ProgramsListContract.Presenter = ProgramsListPresenter(repo)

    @Provides
    fun providesTrainingPresenter(@Named(REPOSITORY) repo: DiaryEntryRepository):
            TrainingContract.Presenter = TrainingPresenter(repo)

    @Provides
    fun providesUserParametersPresenter(@Named(REPOSITORY) repo: DiaryEntryRepository):
            UserParametersContract.Presenter = UserParametersPresenter(repo)


}