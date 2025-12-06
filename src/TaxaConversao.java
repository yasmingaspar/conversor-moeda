public class TaxaConversao {

    private String result;
    private String base_code;
    private String target_code;
    private double conversion_rate;

    public TaxaConversao() {
    }

    public String getResult() {
        return result;
    }

    public double getConversionRate() {
        return conversion_rate;
    }

    public String getBaseCode() {
        return base_code;
    }

    public String getTargetCode() {
        return target_code;
    }


    @Override
    public String toString() {
        return String.format(
                "A taxa de conversão atual de %s para %s é de: %.4f",
                base_code, target_code, conversion_rate
        );

    }
}