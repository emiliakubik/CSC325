public abstract class Skill {
    //attributes
    protected String name;
    protected String type;
    protected int skillLevel;

   //constructor- initializes the name, type, and skillLevel attributes 
    public Skill(String name, String type, int skillLevel){
        this.name = name;
        this.type = type;
        this.skillLevel = skillLevel;
    }

    //getter method retrieves name of the skill
    public String getName(){
        return name;
    }

    //getter method retrieves type of skill (hard, soft, talent, virtue)
    public String getType(){
        return type;
    }

    //getter method retrieves 1-10 rating of the proficiency level of the skill
    public int getSkillLevel(){
        return skillLevel;
    }

    //outputs a message in the terminal tying together the type, name and level of skill
    public abstract void identifySkill();
}
