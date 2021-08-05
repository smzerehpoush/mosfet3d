package me.mahdiyar;

public class MosOperation2 extends MosOperation {

    public MosOperation2(FunctionData functiondata) {
        super(functiondata, new MosDevCircuit2());
    }

    @Override
    protected void setVd() {
        super.mosCkt.setVd(super.Vd);
        super.info.setVd(super.Vd);
        super.outputWrap.setCurrentX(super.Vd);
        if (super.isNChannel) {
            if (super.Vgs < super.Vt || super.Vd < super.Vgs - super.Vt) {
                super.transferWrap.showCurrentSpot(false);
                return;
            } else {
                super.transferWrap.showCurrentSpot(true);
                return;
            }
        }
        super.transferWrap.showCurrentSpot(!(super.Vgs > super.Vt) && !(super.Vd > super.Vgs - super.Vt));
    }

    @Override
    protected void setInfo() {
        super.info = new MosInfo2();
    }

    @Override
    public void run() {
        double d = 0.10000000000000001D;
        double d1 = super.mosCkt.getVgsMax();
        do {
            if (super.udVgs.downArrowPressed()) {
                super.Vgs -= d;
                if (super.isNChannel) {
                    if (super.Vgs < 0.0D)
                        super.Vgs = 0.0D;
                } else if (super.Vgs < -d1)
                    super.Vgs = -d1;
                setVgs();
                repaint();
            } else if (super.udVgs.upArrowPressed()) {
                super.Vgs += d;
                if (super.isNChannel) {
                    if (super.Vgs > d1)
                        super.Vgs = d1;
                } else if (super.Vgs > 0.0D)
                    super.Vgs = 0.0D;
                setVgs();
                repaint();
            } else if (super.udVd.downArrowPressed()) {
                super.Vd -= d;
                if (super.isNChannel) {
                    if (super.Vd < 0.0D)
                        super.Vd = 0.0D;
                } else if (super.Vd < super.Vgs)
                    super.Vd = super.Vgs;
                setVd();
                repaint();
            } else if (super.udVd.upArrowPressed()) {
                super.Vd += d;
                if (super.isNChannel) {
                    if (super.Vd > super.Vgs)
                        super.Vd = super.Vgs;
                } else if (super.Vd > 0.0D)
                    super.Vd = 0.0D;
                setVd();
                repaint();
            }
            try {
                Thread.sleep(100L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    @Override
    public void setLabelsVisibility(boolean state) {
        if (mosCkt != null)
            mosCkt.setLabelsVisibilityState(state);
    }
}
