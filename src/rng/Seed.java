package rng;

public class Seed {
	private int value;
    
	public Seed(){
		this.value = 0;
	}
	public Seed(int value){
		this.value = value;
	}
	public void setValue(int value){
		this.value = value;
	}
	public int getValue(){
		return this.value;
	}
	public void copy (Seed c){
		this.value = c.getValue();
	}
	public int getTrainerID(){
		MersenneTwister random = new MersenneTwister(value);
		int a = random.nextInt();
		a = random.nextInt();
		return a & 0xFFFF;
	}
	public int getLotteryID(){
		RNG rng = new RNG(this.value);
		rng.advance(4);
		return rng.getTop();
	}
	public int getSecretID(){
		MersenneTwister random = new MersenneTwister(value);
		int a = random.nextInt();
		a = random.nextInt();
		return (a >> 16) & 0xFFFF;
	}

	public int getHour(){
		return (this.value >> 16) & 0xFF;
	}
	public int getFrame(){
		return this.value & 0xFFFF;
	}

	public int pokerusFrame(int first, int last){
		RNG rng = new RNG(this.value);
		rng.advance(first);
		for (int i = first; i <= last; i++){
			if (rng.getTop() == 0x4000 || rng.getTop() == 0x8000 || rng.getTop() == 0xC000){
				return i;
			}
			rng.advance();
		}
		return -1;
	}
};
