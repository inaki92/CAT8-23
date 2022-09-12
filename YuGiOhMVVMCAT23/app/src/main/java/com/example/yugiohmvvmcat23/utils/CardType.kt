package com.example.yugiohmvvmcat23.utils

/**
 * This enum class [CardType] allows you to have static value that you can use
 * as parameters to define specific data.
 *
 * 'Skill Card', 'Spell Card', 'Trap Card', 'Normal Monster', 'Normal Tuner Monster',
 * 'Effect Monster', 'Tuner Monster', 'Flip Monster', 'Flip Effect Monster',
 * 'Flip Tuner Effect Monster', 'Spirit Monster', 'Union Effect Monster', 'Gemini Monster',
 * 'Pendulum Effect Monster', 'Pendulum Normal Monster', 'Pendulum Tuner Effect Monster',
 * 'Ritual Monster', 'Ritual Effect Monster', 'Toon Monster', 'Fusion Monster', 'Synchro Monster',
 * 'Synchro Tuner Monster', 'Synchro Pendulum Effect Monster', 'XYZ Monster',
 * 'XYZ Pendulum Effect Monster', 'Link Monster', 'Pendulum Flip Effect Monster',
 * 'Pendulum Effect Fusion Monster' or 'Token'
 */
enum class CardType(val typeName: String) {
    SKILL_CARD("Skill Card"),
    SPELL_CARD("Spell Card"),
    TRAP_CARD("Trap Card"),
    NORMAL_MONSTER("Normal Monster")

}