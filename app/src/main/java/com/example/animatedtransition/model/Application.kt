package com.example.animatedtransition.model

import androidx.annotation.StringRes
import com.example.animatedtransition.R
import java.io.Serializable

data class Application(
        val name: String,
        val description: String,
        val iconResId: Int,
        val coverResId: Int,
        val type: Type
) : Serializable {
    enum class Type(@StringRes val titleResId: Int? = null) {
        GAME_OF_THE_DAY(R.string.game_of_the_day),
        APP()
    }

    companion object {
        private const val STAR_TREK_NAME = "Star Trek\nFeel Command"
        private const val STAR_TREK_DESCRIPTION = "You have the conn! Summon your skills in strategy, combat, diplomacy, and leadership to master the dangerous universe of Star Trek Fleet Command.\n" +
                "\n" +
                "Enter a galaxy on the brink of war as Federation, Klingon, and Romulan forces vie for control of the Alpha and Beta quadrants. Discover an ancient secret that could tip the scales of power forever.\n" +
                "\n" +
                "As the commander of a starbase on the edge of civilized space, you will recruit iconic officers like James T. Kirk, Spock, and Nero -- and build powerful ships including the Enterprise, the Romulan Warbird, and Klingon Bird of Prey.\n" +
                "\n" +
                "Join Millions of players -- forge alliances, defeat your enemies, and build an epic fleet to secure, or dominate, the galaxy.\n" +
                "\n" +
                "Explore strange new worlds, seek out new life and new civilizations, boldly go where no one has gone before!\n" +
                "Create or join powerful alliances to dominate star systems and build the most powerful empire in the galaxy.\n" +
                "\n" +
                "Be prepared to:\n" +
                "- Experience epic conflict in a vast, dynamic galaxy\n" +
                "- Collect, build, and upgrade iconic ships \n" +
                "- Encounter famous Star Trek™ characters in an epic, galaxy-spanning storyline\n" +
                "- Help locals, fight pirates, or negotiate peace in hundreds of unique missions\n" +
                "- Recruit famous officers with unique, tactical abilities\n" +
                "- Compete in faction wars & Lead your Starfleet to Glory \n" +
                "- Ally yourself with Federation, Klingon, or Romulan forces as they clash among the stars\n" +
                "- War is on the rise - Occupy enemies worlds and prepare for new galaxy order\n" +
                "- Work with and against thousands of other players in real-time\n" +
                "- Build, upgrade, and defend your star base\n" +
                "- Defeat your enemies in epic star wars & battles\n" +
                "- Discover new technologies, ship upgrades, and resources\n" +
                "- Take command of your fleet and conquer the universe\n" +
                "\n" +
                "Key Features:\n" +
                "- An open world, strategy MMO\n" +
                "- Mobile Free to play\n" +
                "- Stunning graphics\n" +
                "- Iconic Star Trek™ characters, ships and tech\n" +
                "- Fierce battles with players all over the world\n" +
                "- A New, immersive Star Trek™ story in the Kelvin Timeline\n" +
                "Become the leader or member of a mighty Alliance\n" +
                "- Multiple language options\n" +
                "\n" +
                "Download Star Trek - Fleet Command today and join Millions all over the world.\n" +
                "\n" +
                "©2019 Scopely, Inc. All rights reserved.\n" +
                "TM & ©2019 CBS Studios Inc. ©2019 Paramount Pictures Corp. STAR TREK™ and related marks and logos are trademarks of CBS Studios Inc. All Rights Reserved."

        val STAR_TREK_PROMO = Application(
                STAR_TREK_NAME,
                STAR_TREK_DESCRIPTION,
                R.drawable.ic_star_trek,
                R.drawable.ic_star_trek_logo,
                Type.GAME_OF_THE_DAY)

        val STAR_TREK = Application(
                STAR_TREK_NAME,
                STAR_TREK_DESCRIPTION,
                R.drawable.ic_star_trek,
                R.drawable.ic_star_trek_logo,
                Type.APP)
    }
}