package dev.rarmijo.horoscopefun.presentation.screens.luck_screen

import dev.rarmijo.horoscopefun.R
import dev.rarmijo.horoscopefun.domain.models.LuckCard
import javax.inject.Inject
import kotlin.random.Random

class RandomCardProvider @Inject constructor(){
    fun getRandomCard():LuckCard?{
        return when(Random.nextInt(0, 30)){
            0 -> LuckCard(R.drawable.card_fool, R.string.luck_0, R.string.fool)
            1 -> LuckCard(R.drawable.card_moon, R.string.luck_1, R.string.moon)
            2 -> LuckCard(R.drawable.card_hermit, R.string.luck_2, R.string.hermit)
            3 -> LuckCard(R.drawable.card_star, R.string.luck_3, R.string.star)
            4 -> LuckCard(R.drawable.card_sun, R.string.luck_4,  R.string.sun)
            5 -> LuckCard(R.drawable.card_sword, R.string.luck_5, R.string.sword)
            6 -> LuckCard(R.drawable.card_chariot, R.string.luck_6, R.string.chariot)
            7 -> LuckCard(R.drawable.card_death, R.string.luck_7, R.string.death)
            8 -> LuckCard(R.drawable.card_devil, R.string.luck_8, R.string.devil)
            9 -> LuckCard(R.drawable.card_empress, R.string.luck_9, R.string.empress)
            10 -> LuckCard(R.drawable.card_hierophant, R.string.luck_10, R.string.hierophant)
            11 -> LuckCard(R.drawable.card_ace_pentacles, R.string.luck_11, R.string.ace_pentacles)
            12 -> LuckCard(R.drawable.card_judgement, R.string.luck_12, R.string.judgement)
            13 -> LuckCard(R.drawable.card_world, R.string.luck_13, R.string.world)
            14 -> LuckCard(R.drawable.card_wheel_fortune, R.string.luck_14, R.string.wheel_fortune)
            15 -> LuckCard(R.drawable.card_tower, R.string.luck_15, R.string.tower)
            16 -> LuckCard(R.drawable.card_temperance, R.string.luck_16, R.string.temperance)
            17 -> LuckCard(R.drawable.card_strength, R.string.luck_17, R.string.strength)
            18 -> LuckCard(R.drawable.card_queen_wands, R.string.luck_18, R.string.queen_wands)
            19 -> LuckCard(R.drawable.card_queen_swords, R.string.luck_19, R.string.queen_swords)
            20 -> LuckCard(R.drawable.card_priestess, R.string.luck_20,  R.string.priestess)
            21 -> LuckCard(R.drawable.card_nine_wands, R.string.luck_21, R.string.nine_wands)
            22 -> LuckCard(R.drawable.card_page_wands, R.string.luck_22, R.string.page_wands)
            23 -> LuckCard(R.drawable.card_magician, R.string.luck_23, R.string.magician)
            24 -> LuckCard(R.drawable.card_two_pentacles, R.string.luck_24, R.string.two_pentacles)
            25 -> LuckCard(R.drawable.card_queen_pentacles, R.string.luck_25, R.string.queen_pentacles)
            26 -> LuckCard(R.drawable.card_justice, R.string.luck_26, R.string.justice)
            27 -> LuckCard(R.drawable.card_king_swords, R.string.luck_27, R.string.king_swords)
            28 -> LuckCard(R.drawable.card_king_wands, R.string.luck_28, R.string.king_wands)
            29 -> LuckCard(R.drawable.card_king_cups, R.string.luck_29, R.string.king_cups)
            30 -> LuckCard(R.drawable.card_king_pentacles, R.string.luck_30, R.string.king_pentacles)
            else -> null
        }
    }
}
