package labs;

public class Term implements Comparable<Term>{
    //instance  variable
    private int coefficient;
    private int exponent;

    private static final int DEFAULT_COEFFECIENT = 1;
    private static final int DEFAULT_EXPONENT = 1;

    //constructors
    /*****Default Constructor*****/
    public Term(){
        this(DEFAULT_COEFFECIENT, DEFAULT_EXPONENT);
    }

    /*****Full Constructor*****/
    public Term(int coefficient, int exponent){
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    /*****String Constructor*****/
    public Term(String term){
           if(term.equals("")){
               this.coefficient = 0;
               this.exponent = 0;
           }else if(!term.contains("x")){
               this.coefficient = Integer.parseInt(term);
               this.exponent = 0;
           }else if(!term.contains("^") && term.contains("x")){
               if(term.equals("+x")){
               this.coefficient = 1;
               this.exponent = 1;
           }else if(term.equals("-x")){
               this.coefficient = -1;
               this.exponent = 1;
           }else{
               String coefficientTerm = term.substring(0, term.length()-1);
               this.coefficient = Integer.parseInt(coefficientTerm);
               this.exponent = 1;
           }
       }else if(term.contains("^") && term.contains("x")){
           String coeficientTerm = term.substring(0, term.indexOf("x")+1);
           String exponentTerm = term.substring(term.indexOf("^")+1, term.length());
           this.exponent = Integer.parseInt(exponentTerm);
           if(coeficientTerm.equals("+x")){
               this.coefficient = 1;
           }else if(coeficientTerm.equals("-x")){
               this.coefficient = -1;
           }else{
               coeficientTerm = coeficientTerm.substring(0, coeficientTerm.length()-1);
               this.coefficient = Integer.parseInt(coeficientTerm);
               // this.coefficient = Integer.parseInt(coeficientTerm);// "+7x"
           }
           }
       }
        
        
    

    /*****Copy Constructor*****/
    /***The purpose of this method is to create a copy of any variable. However we need the term to be a deep copy of the original term. meaning that the term points to its own place in memory but contains the same values. 
    ******/
    public Term(Term otherTerm) {
           // Validate the input to ensure 'otherTerm' is not null to avoid NullPointerException.
           if (otherTerm == null) {
               throw new IllegalArgumentException("Null argument detected");
           }
           this.coefficient = otherTerm.coefficient;
           this.exponent = otherTerm.exponent;
       }

    public Term clone(){
       return new Term(this.coefficient, this.exponent);
    }

    /**********Setters*************/
    public void setCoefficient(int coefficient){
       this.coefficient = coefficient;
    }
    
    public void setExponent(int exponent){
       this.exponent = exponent;
    }
    
    public void setTerm(int coefficient, int exponent){
       this.setCoefficient(coefficient);
       this.setExponent(exponent);
    }
    
    public void setAll(int coefficient, int exponent){
       this.setCoefficient(coefficient);
       this.setExponent(exponent);
    }

    /**********Getters*************/
    public int getCoefficient(){
        return coefficient;
    }

    public int getExponent(){
        return exponent;
    }
    /**********toString*************/
    public String toString(){
           if(this.coefficient == 0){
               return "";
           }else if(this.exponent == 0 && this.coefficient > 0){
               return "+" + this.coefficient;
           }else if(this.exponent == 0 && this.coefficient < 0){
               return "" + this.coefficient;
           }else if(this.exponent == 1 && this.coefficient == 1){
               return "+x";
           }else if(this.exponent == 1 && this.coefficient == -1){
               return "-x";
           }else if(this.exponent == 1 && this.coefficient > 1){
               return "+" + this.coefficient +"x";
           }else if(this.exponent == 1 && this.coefficient < 1){
               return "" + this.coefficient + "x";
           }else if(this.coefficient ==1){
               return "+x^" + this.exponent;
           }else if(this.coefficient == -1){
               return "-x^" + this.exponent;
           }else if(this.coefficient > 1){
               return "+" + this.coefficient + "x^" + this.exponent;
           }else if(this.coefficient < 1){
               return "" + this.coefficient + "x^" + this.exponent;
           }
           return "Unexpected term format"; ///return term; 
       }

    /**********Equalss*************/
    public boolean equals(Object obj){
       if(obj instanceof Term){
       Term other = (Term) obj;
       return (other.coefficient == this.coefficient && other.exponent == this.exponent);
        }
    return false;
    }
    
    public int compareTo(Term term){
           if(this.exponent > term.exponent){
               return 1;
           }else if(this.exponent < term.exponent){
               return -1;
           }else{
               return 0;
           }
    }
}