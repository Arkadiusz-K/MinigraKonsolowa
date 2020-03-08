public class Arena {

    void tura(Przeciwnik hero, Przeciwnik pr){
            while(true) {
                obrona(hero, pr);
                if (!sprawdzCzyZyje(hero)) return;
                atakBronia(hero, pr);
                if (!sprawdzCzyPrzeciwnikZyje(hero, pr)) return;
                System.out.println("Doszlismy do konca tury");
            }
    }

    void atakBronia(Przeciwnik hero, Przeciwnik pr){
        System.out.println("ilosc hp przeciwnika: "+pr.gethp());
        pr.sethp(pr.gethp()-(hero.getbron().getobrazenia()*hero.getmoc()));
        System.out.println("hp przeciwnika po ataku mieczem: "+pr.gethp());
    }

    void obrona(Przeciwnik hero, Przeciwnik pr){
        int szansaObrony = (int)(Math.random()*100);
        System.out.println("Szansa obrony: "+szansaObrony);
        System.out.println("ilosc zycia przed atakiem "+hero.gettyp()+" to: "+hero.gethp());
        if(szansaObrony>50) return;
        System.out.println("Nie udalo sie obronic przed atakiem: "+pr.gettyp() +":(");
        hero.sethp(hero.gethp()-pr.getmoc());
        System.out.println("ilosc zycia po ataku "+hero.gettyp()+" to: "+hero.gethp());

    }

    void sprawdzLvl(Przeciwnik hero){
        if((hero.getexp()-hero.getWymaganeDoLvl())>=0) {
            hero.setlvl(hero.getlvl() + 1);
            double doNastepnegoLvl = hero.getexp()-hero.getWymaganeDoLvl();
            System.out.println(hero.gettyp()+" zdobywa lvl!");
            System.out.println("Teraz jego lvl to: "+hero.getlvl());
            hero.setexp(doNastepnegoLvl);
            hero.sethp(hero.gethp()+2);
            hero.setmoc(hero.getmoc()+1);
            System.out.println("ilosc exp: "+hero.getexp());
            if(doNastepnegoLvl>=hero.getWymaganeDoLvl()) sprawdzLvl(hero);
        }
    }

    boolean sprawdzCzyZyje(Przeciwnik hero){
        if(hero.gethp()<0){
            System.out.println(hero.gettyp()+" przegral, tracisz zycie");
            hero.setIloscZyc(hero.getilosczyc()-1);
            return false;
        } else
            return true;
    }

    boolean sprawdzCzyPrzeciwnikZyje(Przeciwnik hero,Przeciwnik pr){
        if(pr.gethp()<0) {
            System.out.println(pr.gettyp() + " przegrywa!");
            hero.setexp(hero.getexp() + pr.getexp());
            sprawdzLvl(hero);
            return false;
        } else return true;
    }
}
