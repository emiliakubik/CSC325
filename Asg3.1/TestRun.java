public class TestRun {
    //main method will run test code to test each 4 of the concrete classes
    public static void main(String[] args) {
        HardSkill h = new HardSkill("programming", 8);
        SoftSkill s = new SoftSkill("communication", 6);
        GiftSkill g = new GiftSkill("mercy", 9);
        TalentSkill t = new TalentSkill("organization", 7);

        h.identifySkill();
        s.identifySkill();
        g.identifySkill();
        t.identifySkill();
    }
}
