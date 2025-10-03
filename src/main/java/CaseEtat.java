public enum CaseEtat {
    BOIS(0x1F384),
    FEU(0x1F525),
    CENDRE(0x1F301);

    public final int unicode;

    CaseEtat(int unicode) {
        this.unicode = unicode;
    }
}
