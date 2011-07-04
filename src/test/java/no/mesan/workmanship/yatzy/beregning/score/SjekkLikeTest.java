package no.mesan.workmanship.yatzy.beregning.score;

import org.junit.Test;

import no.mesan.workmanship.yatzy.beregning.score.ResultatPar;
import no.mesan.workmanship.yatzy.beregning.score.SjekkLike;
import no.mesan.workmanship.yatzy.domene.TerningPoeng;
import no.mesan.workmanship.yatzy.domene.TestKast;

import static org.junit.Assert.*;

import static no.mesan.workmanship.yatzy.domene.TestTerning.*;

public class SjekkLikeTest {
    private static final TestKast STRAIGHT= new TestKast(_1, _2, _3, _4, _5);
    private static final TestKast HUS= new TestKast(_2, _3, _3, _2, _2);
    private static final TestKast YATZY= new TestKast(_2, _2, _2, _2, _2);

    @Test
    public void beste_par_finnes_ikke() {
        assertNull(SjekkLike.bestePar(STRAIGHT));
    }

    @Test
    public void mange_par() {
        assertEquals(TerningPoeng.TO, SjekkLike.bestePar(YATZY));
    }

    @Test
    public void velger_beste_par() {
        assertEquals(TerningPoeng.TRE, SjekkLike.bestePar(HUS));
    }

    @Test
    public void har_ikke_3like() {
        assertNull(SjekkLike.trippel(STRAIGHT));
    }

    @Test
    public void har_3like() {
        assertEquals(TerningPoeng.TO, SjekkLike.trippel(HUS));
        assertEquals(TerningPoeng.TO, SjekkLike.trippel(YATZY));
    }

    @Test
    public void har_ikke_4like() {
        assertNull(SjekkLike.kvadruppel(HUS));
    }

    @Test
    public void har_4like() {
        assertEquals(TerningPoeng.TO, SjekkLike.kvadruppel(YATZY));
    }

    @Test
    public void har_ikke_yatzy() {
        assertNull(SjekkLike.yatzy(HUS));
    }

    @Test
    public void har_yatzy() {
        assertEquals(TerningPoeng.TO, SjekkLike.yatzy(YATZY));
    }

    @Test
    public void har_hus_3_2() {
        final ResultatPar hus= SjekkLike.hus(HUS);
        assertNotNull(hus);
        assertEquals(TerningPoeng.TO, hus.maksimalVerdi);
        assertEquals(TerningPoeng.TRE, hus.minimumsVerdi);
    }

    @Test
    public void har_hus_2_2() {
        final ResultatPar hus= SjekkLike.hus(YATZY);
        assertNotNull(hus);
        assertEquals(TerningPoeng.TO, hus.maksimalVerdi);
        assertEquals(TerningPoeng.TO, hus.minimumsVerdi);
    }

    @Test
    public void har_ikke_hus() {
        final ResultatPar hus= SjekkLike.hus(STRAIGHT);
        assertNull(hus);
    }
}
