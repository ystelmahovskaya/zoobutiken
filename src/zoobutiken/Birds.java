
package zoobutiken;


public class Birds extends AliveIndividuals {
    
    private String species;

    public Birds(String breed, int age, boolean isPredator,String species ) {
        super(breed, age, isPredator);
        this.species=species;
    }

 
    public String getSpecies() {
        return species;
    }

    
    public void setSpecies(String species) {
        this.species = species;
    }
    
    
}
