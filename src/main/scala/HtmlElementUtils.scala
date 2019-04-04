import net.ruippeixotog.scalascraper.model.Element

object HtmlElementUtils {
  /**
    * Returns the full path of the given element
    * @param element
    * @return
    */
  def getElementPath(element: Element): String = {
    val prev = element.parent match {
      case Some(e) =>
        val idx = e.children.zipWithIndex.find(el => el._1 == element).map(_._2).getOrElse(-1)
        s"${getElementPath(e)}[$idx]-> "

      case _ => ""
    }

    prev + element.tagName
  }

}
