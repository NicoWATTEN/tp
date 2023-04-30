import com.github.polomarcus.utils.ClimateService
import com.github.polomarcus.model.CO2Record
import org.scalatest.funsuite.AnyFunSuite

//@See https://www.scalatest.org/scaladoc/3.1.2/org/scalatest/funsuite/AnyFunSuite.html
class ClimateServiceTest extends AnyFunSuite {
  test("containsWordGlobalWarming - non climate related words should return false") {
    assert( !ClimateService.isClimateRelated("pizza"))
  }

  test("isClimateRelated - climate related words should return true") {
    assert(ClimateService.isClimateRelated("climate change"))
    assert(ClimateService.isClimateRelated("IPCC"))
  }

  test("isClimateRelated - sentence with climate-related words should return true") {
    assert(ClimateService.isClimateRelated("The IPCC's report on global warming is alarming."))
  }

  //@TODO
  test("parseRawData") {
    // our inputs
    val firstRecord = (2003, 1, 355.2)     //help: to acces 2003 of this tuple, you can do firstRecord._1
    val secondRecord = (2004, 1, 375.2)
    val list1 = List(firstRecord, secondRecord)

    // our output of our method "parseRawData"
    val co2RecordWithType = CO2Record(firstRecord._1, firstRecord._2, firstRecord._3)
    val co2RecordWithType2 = CO2Record(secondRecord._1, secondRecord._2, secondRecord._3)
    val output = List(Some(co2RecordWithType), Some(co2RecordWithType2))

    // we call our function here to test our input and output
    assert(ClimateService.parseRawData(list1) == output)
  }

  test("getMinMax - returns min and max ppm values") {
    val records = List(
      CO2Record(2003, 1, 355.2),
      CO2Record(2004, 1, 375.2),
      CO2Record(2005, 1, 365.1)
    )
    val result = ClimateService.getMinMax(records)
    assert(result == (355.2, 375.2))
  }

  test("getMinMaxForYear - valid year") {
    val records = List(
      CO2Record(2019, 1, 411.1),
      CO2Record(2019, 2, 411.5),
      CO2Record(2020, 1, 414.2),
      CO2Record(2020, 2, 415.4)
    )
    assert(ClimateService.getMinMaxByYear(records, 2019) == (411.1, 411.5))
  }
  test("getDifference should return the difference between max and min ppm values") {
    val records = List(
      CO2Record(1958, 3, 316.19),
      CO2Record(1958, 4, 317.29),
      CO2Record(1958, 5, 317.87),
      CO2Record(1958, 7, 315.85),
      CO2Record(1958, 8, 313.97),
      CO2Record(1958, 9, 312.44),
    )
    val result = ClimateService.getDifference(records)
    val expected = 5.430000000000007 // difference between 317.87 and 312.44
    assert(result == expected)
  }


  """
  //@TODO
  test("filterDecemberData") {
    assert(true == false)
  }
  """
}