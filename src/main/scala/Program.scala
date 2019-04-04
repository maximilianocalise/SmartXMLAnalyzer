import java.io.FileNotFoundException

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._

object Program extends App {

  if(args.size < 3) {
    println("Invalid parameter number")
  } else {

    try {
      val browser = JsoupBrowser()
      val refDoc = browser.parseFile(args(0))
      val refElement = refDoc >?> element(args(2))

      if(refElement.nonEmpty) {
        val targetDoc = browser.parseFile(args(1))
        val elements = targetDoc >> elementList("*")

        val resElement = HtmlElementMatcher.findClosestElement(elements, refElement.get)

        println(HtmlElementUtils.getElementPath(resElement))
      } else {
        println("Invalid reference element id")
      }
    } catch {
      case e: FileNotFoundException => println("Html file doesn't exist")
      case _ => println("Unexpected exception")
    }
  }

}
