//concrete class that extends from the Skills abstract class
public class TalentSkill extends Skill {
    //constructor for talents
    public TalentSkill(String name, int skillLevel){
        //calls constructor from abstract class
        super(name, "Talent", skillLevel);
    }

    @Override
    //outputs a message in the terminal tying together the type, name and level of skill
    public void identifySkill(){
        System.out.println(type + " of " + name + " has a level of " + skillLevel);
    }
}
