package rng;

public class RNG
{
    public RNG(int v)
    {
        this.value = v;
        this.frame = 0;
    }
    public RNG(Seed seed) {
		this.value = seed.getValue();
		this.frame = 0;
	}
    public RNG(int v, int f)
    {
        this.value = v;
        this.frame = f;
    }
	public void advance()
    {
        this.value = this.value * 0x41C64E6D + 0x6073;
        this.frame++;
    }
    public void advance(int n)
    {
    	this.frame += n;
        int i = 0;
        while (n > 0)
        {
            if (n % 2 != 0)
                this.value = this.value * a[i] + b[i];
            n = n >> 1;
            i++;
            if (i >= 32) break;
        }
    }
	public void decrease()
    {
        this.value = this.value * 0XEEB9EB65 + 0XA3561A1;
        this.frame--;
    }
    public void decrease(int n)
    {
        for (int i = 0; i < n; i++){
        	this.decrease();
        }
    }
    public void gotoFrame(int n){
    	int diff = n - frame;
    	if (diff >= 0){
    		this.advance(diff);
    	}
    	else{
    		for (int i = 0; i < diff; i++){
    			this.value = this.value * 0XEEB9EB65 + 0XA3561A1; //backwards formula
    		}
    	}
    	this.frame = n;
    }
    public RNG getCopy(){
    	return new RNG(this.getValue(),this.getFrame());
    }
    public void setValue(int v){
        this.value = v;
    }
    public int getValue(){
        return this.value;
    }
    public int getFrame(){
		return this.frame;
	}
    public int getTop()
    {
        return (this.value >> 16) & 0xFFFF;
    }
    public int moduloCheck(int check){
    	return this.getTop() % check;
    }
    
    private int value;
    private int frame;

    //The following are helpful to calculate RNG in O(log n)
    
    private int[] a = {
0x41C64E6D, 0xC2A29A69, 0xEE067F11, 0xCFDDDF21,
0x5F748241, 0x8B2E1481, 0x76006901, 0x1711D201,
0xBE67A401, 0xDDDF4801, 0x3FFE9001, 0x90FD2001,
0x65FA4001, 0xDBF48001, 0xF7E90001, 0xEFD20001,
0xDFA40001, 0xBF480001, 0x7E900001, 0xFD200001,
0xFA400001, 0xF4800001, 0xE9000001, 0xD2000001,
0xA4000001, 0x48000001, 0x90000001, 0x20000001,
0x40000001, 0x80000001, 0x00000001, 0x00000001};

    private int[] b = {
0x00006073, 0xE97E7B6A, 0x31B0DDE4, 0x67DBB608,
0xCBA72510, 0x1D29AE20, 0xBA84EC40, 0x79F01880,
0x08793100, 0x6B566200, 0x803CC400, 0xA6B98800,
0xE6731000, 0x30E62000, 0xF1CC4000, 0x23988000,
0x47310000, 0x8E620000, 0x1CC40000, 0x39880000,
0x73100000, 0xE6200000, 0xCC400000, 0x98800000,
0x31000000, 0x62000000, 0xC4000000, 0x88000000,
0x10000000, 0x20000000, 0x40000000, 0x80000000};

	public void copy(RNG rng1) {
		this.value = rng1.getValue();
		this.frame = rng1.getFrame();
	}
}
