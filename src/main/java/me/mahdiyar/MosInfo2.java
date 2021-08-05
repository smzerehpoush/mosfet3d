package me.mahdiyar;

public class MosInfo2 extends MosInfo {

    protected void setVd(double d) {
        double d1 = super.Vgs - super.Vt;
        if (super.nChannel && d < 0.0D)
            d = 0.0D;
        else if (!super.nChannel && d > 0.0D)
            d = 0.0D;
        if (super.Vd >= d1 && d < d1 || super.Vd <= d1 && d > d1)
            super.changed = true;
        super.Vd = d;
    }

    @Override
    public void setVgs(double d) {
        double d1 = super.Vgs - super.Vt;
        double d2 = d - super.Vt;
        if (super.Vgs >= super.Vt && d < super.Vt || super.Vgs <= super.Vt && d > super.Vt || super.Vd >= d1 && super.Vd < d2 || super.Vd <= d1 && super.Vd > d2)
            super.changed = true;
        super.Vgs = d;
    }

    @Override
    protected void setDescription() {
        if (super.nChannel) {
            if (super.Vgs < super.Vt) {
                super.info[1] = "";
                super.info[2] = "";
                super.info[3] = "";
                return;
            }
            if (super.Vd >= super.Vgs - super.Vt) {
                super.info[1] = "Vds > Vds(sat) = Vgs - Vt : Drain-side of channel is pinched-off.";
                super.info[2] = "Id is saturated : Ids = 0.5k (Vgs - Vt)^2 ";
                super.info[3] = "The MOS Transistor is now 'ON'.";
            } else {
                super.info[1] = "Vds < Vds(sat) = Vgs - Vt : Drain-side of channel is continuous.";
                super.info[2] = "Id varies with Vds : Id = k [(Vgs - Vt)*Vds - Vds*Vds/2] ";
                super.info[3] = "The MOS Transistor acts as a Triode.";
            }
            return;
        }
        if (super.Vgs > super.Vt) {
            super.info[1] = "";
            super.info[2] = "";
            super.info[3] = "";
            return;
        }
        if (super.Vd <= super.Vgs - super.Vt) {
            super.info[1] = "Vds < Vds(sat) = Vgs - Vt : Drain-side of channel is pinched-off.";
            super.info[2] = "Id is saturated : Ids = 0.5k (Vgs - Vt)^2 ";
            super.info[3] = "The MOS Transistor is now 'ON'.";
        } else {
            super.info[1] = "Vds > Vds(sat) = Vgs - Vt : Drain-side of channel is continuous.";
            super.info[2] = "Id varies with Vds : Id = k [(Vgs - Vt)*Vds - Vds*Vds/2] ";
            super.info[3] = "The MOS Transistor acts as a Triode.";
        }
    }
}
