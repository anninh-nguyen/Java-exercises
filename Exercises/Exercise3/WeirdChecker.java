package Exercise3;

public class WeirdChecker {
    private int totalCodes;
    private int passingCodes;

    public WeirdChecker(String codes){
        var codesSplited =  codes.split("\\!");

        int count = 0;
        for (int i = 0; i < codesSplited.length; i++){
            if (codesSplited[i].matches("\\d{5}-\\d{5}")){
                count++;
            }
        }
        passingCodes = count;
        if (codes == ""){
            totalCodes = 0;
        }
        else{
            totalCodes = codesSplited.length;
        }
        
    }

    public int getTotalCodes() { return totalCodes; }

    public int getPassingCodes() { return passingCodes; }

    public void printStats(){
        System.out.println("Passing:" + passingCodes);
        System.out.println("Total:" + totalCodes);
    }
}
