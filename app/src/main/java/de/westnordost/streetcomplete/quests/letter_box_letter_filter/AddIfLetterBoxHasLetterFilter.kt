package de.westnordost.streetcomplete.quests.letter_box_letter_filter

import de.westnordost.streetcomplete.R
import de.westnordost.streetcomplete.data.osm.geometry.ElementGeometry
import de.westnordost.streetcomplete.data.osm.mapdata.Element
import de.westnordost.streetcomplete.data.osm.mapdata.MapDataWithGeometry
import de.westnordost.streetcomplete.data.osm.mapdata.filter
import de.westnordost.streetcomplete.data.osm.osmquests.OsmFilterQuestType
import de.westnordost.streetcomplete.data.user.achievements.EditTypeAchievement
import de.westnordost.streetcomplete.osm.Tags
import de.westnordost.streetcomplete.quests.YesNoQuestForm
import de.westnordost.streetcomplete.util.ktx.toYesNo

class AddIfLetterBoxHasLetterFilter  : OsmFilterQuestType<Boolean>() {
    override val elementFilter = """
        nodes with
         amenity = letter_box
         and !letter_filter
    """
    override val changesetComment = "Determine whether letter boxes has letter filter"
    override val wikiLink = "Key:letter_filter"
    override val icon = R.drawable.ic_quest_mail
    override val achievements = listOf(EditTypeAchievement.POSTMAN)

    override fun getTitle(tags: Map<String, String>) = R.string.quest_letter_box_letter_filter

    override fun getHighlightedElements(element: Element, getMapData: () -> MapDataWithGeometry) =
        getMapData().filter("nodes with amenity = letter_box")

    override fun createForm() = YesNoQuestForm()

    override fun applyAnswerTo(answer: Boolean, tags: Tags, geometry: ElementGeometry, timestampEdited: Long) {
        tags["letter_filter"] = answer.toYesNo()
    }
}
