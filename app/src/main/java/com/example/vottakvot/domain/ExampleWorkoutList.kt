import androidx.compose.ui.platform.LocalContext
import com.example.vottakvot.R
import com.example.vottakvot.domain.BodyType
import com.example.vottakvot.domain.InquirerPage
import com.example.vottakvot.domain.OnBoardingAnswerDataItem
import com.example.vottakvot.domain.WorkoutDataItem
import kotlin.random.Random

/*private val sourceList = mutableListOf<WorkoutItem>().apply {
     repeat(10) {
         add(WorkoutItem(id = it))
     }
}*/


public val sourceListAnswersQuestionTrainTypeExample = mutableListOf<OnBoardingAnswerDataItem>().apply {
    val answerItem_0 = OnBoardingAnswerDataItem(id = 0, question = "Какими типами тренировок"  +
            "Вы бы хотели заниматься чаще?", questionId = 0, answer = "Утренняя зарядка", 
        isOn = false)
    add(answerItem_0)
    val answerItem_1 = OnBoardingAnswerDataItem(id = 1, question = "Какими типами тренировок " +
            "Вы бы хотели заниматься чаще?", questionId = 0, answer = "Фитнес дома",
        isOn = false)
    add(answerItem_1)
    val answerItem_2 = OnBoardingAnswerDataItem(id = 2, question = "Какими типами тренировок " +
            "Вы бы хотели заниматься чаще?", questionId = 0, answer = "Разминка на работе",
        isOn = false)
    add(answerItem_2)
    val answerItem_3 = OnBoardingAnswerDataItem(id = 3, question = "Какими типами тренировок " +
            "Вы бы хотели заниматься чаще?", questionId = 0, answer = "Утренняя зарядка",
        isOn = false)
    add(answerItem_3)
}

public val sourceListAnswersQuestionBodyPartExample = mutableListOf<OnBoardingAnswerDataItem>().apply {
    val answerItem_0 = OnBoardingAnswerDataItem(id = 0, question = "Какие части тела Вы бы хотели " +
            "чаще прорабатывать при тренировках?", questionId = 1, answer = "Верхняя часть тела",
        isOn = false)
    add(answerItem_0)
    val answerItem_1 = OnBoardingAnswerDataItem(id = 1, question = "Какими типами тренировок " +
            "Вы бы хотели заниматься чаще?", questionId = 1, answer = "Нижняя часть тела",
        isOn = false)
    add(answerItem_1)
    val answerItem_2 = OnBoardingAnswerDataItem(id = 2, question = "Какими типами тренировок " +
            "Вы бы хотели заниматься чаще?", questionId = 1, answer = "Пресс",
        isOn = false)
    add(answerItem_2)
    val answerItem_3 = OnBoardingAnswerDataItem(id = 3, question = "Какими типами тренировок " +
            "Вы бы хотели заниматься чаще?", questionId = 1, answer = "Всё тело",
        isOn = false)
    add(answerItem_3)
}


public val sourceListMyTrainsExample = mutableListOf<WorkoutDataItem>().apply {
    val workoutItem =  WorkoutDataItem(0, "Здоровая спина", 7, BodyType.BOTTOM_BODY,
        0, false, false, false)
    add(workoutItem)
    add(workoutItem)
    add(workoutItem)
    add(workoutItem)
    add(workoutItem)
}

public val sourceListTrainsForYouExample = mutableListOf<WorkoutDataItem>().apply {
    val workoutItem_0 =  WorkoutDataItem(0, "Взбодрись утром!", 10,  BodyType.FULL_BODY,
        0, false, false, false)
    add(workoutItem_0)
    val workoutItem_1 =  WorkoutDataItem(1, "Сбросить стресс перед сном", 9, BodyType.UPPER_BODY,
        0, false, true, false)
    add(workoutItem_1)
    val workoutItem_2 =  WorkoutDataItem(2, "Стальной пресс за 8 минут", 7, BodyType.ABD,
        0, false, true, false)
    add(workoutItem_2)
    val workoutItem_3 =  WorkoutDataItem(3, "Сильные руки", 11, BodyType.UPPER_BODY,
        0, false, true, false)
    add(workoutItem_3)
    val workoutItem_4 =  WorkoutDataItem(4, "Здоровая спина", 7, BodyType.FULL_BODY,
        0, false, true, false)
    add(workoutItem_4)
    val workoutItem_5 =  WorkoutDataItem(4, "Здоровые ноги", 7, BodyType.BOTTOM_BODY,
        0, false, true, false)
    add(workoutItem_5)

}
public val sourceListPopularExample = mutableListOf<WorkoutDataItem>().apply {
    val workoutItem_0 =  WorkoutDataItem(0, "Здоровые ноги", 7, BodyType.BOTTOM_BODY,
        0, false, true, false)
    add(workoutItem_0)
    var workoutItem_1 =  WorkoutDataItem(1, "Стальной пресс за 8 минут", 7, BodyType.FULL_BODY,
        0, false, true, false)
    add(workoutItem_1)
    val workoutItem_2 =  WorkoutDataItem(2, "Стальной пресс за 8 минут", 7, BodyType.ABD,
        0, false, true, false)
    add(workoutItem_2)
    val workoutItem_3 =  WorkoutDataItem(3, "Здоровые ноги", 4, BodyType.BOTTOM_BODY,
        0, false, true, false)
    add(workoutItem_3)
    val workoutItem_4 =  WorkoutDataItem(4, "Сбросить стресс перед сном", 9, BodyType.ABD,
        0, false, true, false)
    add(workoutItem_4)
    val workoutItem_5 =  WorkoutDataItem(5, "Здоровые ноги", 7, BodyType.FULL_BODY,
        0, false, true, false)
    add(workoutItem_5)
    val workoutItem_6 =  WorkoutDataItem(26, "Стальной пресс за 8 минут", 7, BodyType.ABD,
        0, false, true, false)
    add(workoutItem_6)
    val workoutItem_7 =  WorkoutDataItem(7, "Здоровые ноги", 7, BodyType.BOTTOM_BODY,
        0, false, true, false)
    add(workoutItem_7)
    val workoutItem_8 =  WorkoutDataItem(8, "Взбодрись утром!", 10,  BodyType.FULL_BODY,
        0, false, false, false)
    add(workoutItem_8)
}

private val sourceListFavouriteExample = mutableListOf<WorkoutDataItem>().apply {
    val workoutItem =  WorkoutDataItem(0, "Здоровая спина", 7, BodyType.FULL_BODY,
        0, false, false, false)
    add(workoutItem)
    add(workoutItem)
    add(workoutItem)
    add(workoutItem)
    add(workoutItem)
}

public val sourceListSearchResultExample = mutableListOf<WorkoutDataItem>().apply {
    val workoutItem_0 =  WorkoutDataItem(0, "Сбросить стресс перед сном", 9, BodyType.ABD,
        0, false, true, false)
    add(workoutItem_0)
    val workoutItem_1 =  WorkoutDataItem(0, "Сбросить вес перед сном", 10, BodyType.UPPER_BODY,
        0, true, true, false)
    add(workoutItem_1)
    val workoutItem_2 =  WorkoutDataItem(0, "Гимнастика для улучшения сна", 3, BodyType.ABD,
        0, false, true, false)
    add(workoutItem_2)
    val workoutItem_3 =  WorkoutDataItem(0, "Вечерняя растяжка", 9, BodyType.UPPER_BODY,
        0, false, false, false)
    add(workoutItem_3)
    val workoutItem_4 =  WorkoutDataItem(0, "Йога для релаксации", 5, BodyType.UPPER_BODY,
        0, true, false, false)
    add(workoutItem_4)
    val workoutItem_5 =  WorkoutDataItem(0, "Спокойное настроение", 6, BodyType.UPPER_BODY,
        0, false, false, false)
    add(workoutItem_5)
    val workoutItem_6 =  WorkoutDataItem(0, "Восстановительные упражнения", 8, BodyType.ABD,
        0, false, false, false)
    add(workoutItem_6)
}
