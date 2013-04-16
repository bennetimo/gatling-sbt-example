import org.specs2.mutable.Specification

class ExampleSpec extends Specification{

  "The string 'Gatling Sbt Plugin'" should {
    "contain 18 characters" in {
      "Gatling Sbt Plugin" must have size(18)
    }
  }

}
