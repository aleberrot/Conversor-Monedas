package modelos;

import java.util.Map;

public class Exchange{
  private final String base_code;
  private final Map<String, Double> conversion_rates;


  public Exchange(String base_code, Map<String, Double> conversion_rates){
    this.base_code = base_code;
    this.conversion_rates = conversion_rates;
  }

  // getters and setters
  public String getBaseCode(){
    return this.base_code;
  }
  public Map<String, Double> getConversionRates(){
    return this.conversion_rates;
  }
  @Override
  public String toString(){
    String result = """
    **********
    Exchange Data

    Base code -> %s
    Conversion rates -> %s

    ***********

    """.formatted(this.base_code, this.conversion_rates);
  
    return result;
  }


}
