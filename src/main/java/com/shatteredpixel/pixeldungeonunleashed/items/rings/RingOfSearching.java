package com.shatteredpixel.pixeldungeonunleashed.items.rings;

public class RingOfSearching  extends Ring {

    {
        name = "Ring of Searching";
        levelCap = 0;
        cursed = false;
        level = 0;
    }

    @Override
    protected RingBuff buff( ) {
        return new EasySearch();
    }

    @Override
    public String desc() {
        return isKnown() ?
                "When wearing this ring brings out of place things into focus "+
                 "making searches easier. "+
                 "A degraded ring makes it tougher to find things." :
                super.desc();
    }

    public class EasySearch extends RingBuff {
    }
}