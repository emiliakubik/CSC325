//concrete class that extends from the Skills abstract class
public class HardSkill extends Skill{
    //constructor for hard skills
    public HardSkill(String name, int skillLevel){
        //calls constructor from abstract class
        super(name, "Hard skill", skillLevel);
    }

    @Override
    //outputs a message in the terminal tying together the type, name and level of skill
    public void identifySkill(){
        System.out.println(type + " of " + name + " has a level of " + skillLevel);
    }
}
