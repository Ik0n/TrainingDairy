package ru.ikon.trainingdairy.di

import dagger.Module
import dagger.Provides
import ru.ikon.trainingdairy.domain.repository.DiaryEntryRepository
import ru.ikon.trainingdairy.domain.repository.DummyDiaryEntryRepositoryImpl
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
import javax.inject.Named

@Module
class DbModule {
    @Provides
    @Named("repository")
    fun providesRepository(): DiaryEntryRepository =
        DummyDiaryEntryRepositoryImpl()

    @Provides
    fun providesAttemptPresenter(@Named("repository") repo: DiaryEntryRepository):
            AttemptContract.Presenter = AttemptPresenter(repo)

    @Provides
    fun providesDayPresenter(@Named("repository") repo: DiaryEntryRepository):
            DayContract.Presenter = DayPresenter(repo)

    @Provides
    fun providesExercisePresenter(@Named("repository") repo: DiaryEntryRepository):
            ExerciseContract.Presenter = ExercisePresenter(repo)

    @Provides
    fun providesExerciseAttemptsPresenter(@Named("repository") repo: DiaryEntryRepository):
            ExerciseAttemptsContract.Presenter = ExerciseAttemptsPresenter(repo)

    @Provides
    fun providesHistoryPresenter(@Named("repository") repo: DiaryEntryRepository):
            HistoryContract.Presenter = HistoryPresenter(repo)

    @Provides
    fun providesMeasurePresenter(@Named("repository") repo: DiaryEntryRepository):
            MeasureContract.Presenter = MeasurePresenter(repo)

    @Provides
    fun providesMonthPresenter(@Named("repository") repo: DiaryEntryRepository):
            MonthContract.Presenter = MonthPresenter(repo)

    @Provides
    fun providesNotePresenter(@Named("repository") repo: DiaryEntryRepository):
            NoteContract.Presenter = NotePresenter(repo)

    @Provides
    fun providesParametersPresenter(@Named("repository") repo: DiaryEntryRepository):
            ParametersContract.Presenter = ParametersPresenter(repo)

    @Provides
    fun providesProgramsListPresenter(@Named("repository") repo: DiaryEntryRepository):
            ProgramsListContract.Presenter = ProgramsListPresenter(repo)

    @Provides
    fun providesTrainingPresenter(@Named("repository") repo: DiaryEntryRepository):
            TrainingContract.Presenter = TrainingPresenter(repo)

    @Provides
    fun providesUserParametersPresenter(@Named("repository") repo: DiaryEntryRepository):
            UserParametersContract.Presenter = UserParametersPresenter(repo)









}