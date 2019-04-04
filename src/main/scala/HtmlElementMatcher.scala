import info.debatty.java.stringsimilarity.SorensenDice
import net.ruippeixotog.scalascraper.model.Element

object HtmlElementMatcher {

  val sd = new SorensenDice()

  /**
    * Gets the Sorensen-Dice similarity
    * @param val1 The first string to compare.
    * @param val2 The second string to compare.
    * @return the Sorensen-Dice similarity
    */
  def getStringSimilarity(val1: String, val2: String): Double = {
    sd.similarity(val1, val2)
  }

  /**
    * Calculates a score representing the similarity between two elements
    * @param refElement Element to be used as the reference
    * @param targetElement Element to be evaluated
    * @return
    */
  def calcSingleElementScore(refElement: Element, targetElement: Element) : Double = {
    val valueScore = getStringSimilarity(refElement.text, targetElement.text)
    val refAttr = refElement.attrs
    val targetAttr = targetElement.attrs
    val attrScore = targetAttr.filter(ta => refAttr.keySet.contains(ta._1)).map(ta => getStringSimilarity(targetAttr(ta._1), refAttr(ta._1))).sum

    valueScore + attrScore
  }

  /**
    * Given a list of elements to be evaluated, return the most similar element to the reference element
    * @param elements Elements list to be evaluated
    * @param refElement Element to be used as reference
    * @return
    */
  def findClosestElement(elements : List[Element], refElement: Element): Element = {
    val elementWithScore = elements.map(e => (e, calcSingleElementScore(refElement, e)))
    elementWithScore.maxBy(e => e._2)._1
  }

}
