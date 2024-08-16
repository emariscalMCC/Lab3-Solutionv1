package labs;

import java.util.LinkedList;
import java.util.ListIterator;


public class Polynomial{
    // Instance variable / Data Field
       private LinkedList<Term> polynomialList;

       // Default Constructor
       public Polynomial() {
           this.polynomialList = new LinkedList<>();
       }

       // Copy Constructor
       public Polynomial(Polynomial otherPolyObj) {
           if (otherPolyObj == null) {
               return;
           }
           LinkedList<Term> poly = otherPolyObj.polynomialList;
           this.polynomialList = new LinkedList<>(poly);
           this.addLikeTerms();
       }

       public void addTerm(Term term){
           this.polynomialList.add(term);
           this.addLikeTerms();
       }

    public void add(Polynomial otherPolyObj){
           LinkedList<Term> poly = otherPolyObj.polynomialList;
           this.polynomialList.addAll(poly);
           this.addLikeTerms();
       }

       public int getNumTerms(){
           return this.polynomialList.size();
       }

       public Term getTerm(int index){
           return this.polynomialList.get(index);
       }

       public void clear(){
           this.polynomialList.clear();
       }

    public void addLikeTerms() {
        //////
        LinkedList<Term> polynomailList2 = new LinkedList<>();
        while(this.polynomialList.size() > 0){
            int exponentTerm = this.polynomialList.get(0).getExponent();
            int coefficientSum = this.polynomialList.get(0).getCoefficient();
            this.polynomialList.removeFirst();
            ListIterator<Term> iterator = this.polynomialList.listIterator(0);
            while(iterator.hasNext()){
                Term temp = iterator.next();
                int expo = temp.getExponent();
                if(exponentTerm == expo){
                   coefficientSum = coefficientSum + temp.getCoefficient();
                   iterator.remove();
                }
           }//end of first while loop
           if(coefficientSum != 0){
               Term newTerm = new Term(coefficientSum, exponentTerm);
               polynomailList2.add(newTerm);
           }
        }//end of second while loop
        for(int i = 0; i < polynomailList2.size(); i++){
            for(int j=1; j< polynomailList2.size(); j++){
                if(polynomailList2.get(j-1).getExponent()< polynomailList2.get(j).getExponent()){
                    Term maxTerm = polynomailList2.get(j);
                    Term minTerm = polynomailList2.get(j-1);
                    polynomailList2.set(j, minTerm);
                    polynomailList2.set(j-1, maxTerm);
                }
            }
        }
        this.polynomialList = polynomailList2;
    }

    public String toString(){
        String output = "";
        if(this.polynomialList.size() == 0){
            output = "0";
        }else{
            ListIterator<Term> iterator = this.polynomialList.listIterator(0);
            Term current = iterator.next();
            
            if(current.getCoefficient() > 0){
               output = current.toString().substring(1);
            }else{
               output = current.toString();
            }
            while(iterator.hasNext()){
               output = output + iterator.next().toString();
            }
        }
        return output;
    }


}
