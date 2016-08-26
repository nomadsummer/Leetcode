package ixllearning;

import java.util.List;
import java.util.Random;

/*
 * IXL plays a sound after correct and incorrect answers. 
 * Implement the below playSound method such that after each correct question a random correct sound is played 
 * and the same correct sound is never played twice in a row.
 * correct sounds -> [a,b,c];
 * incorrect sound -> x;
 * 
 */
public class PlaySound {

	class Sound {}
	class User {
		public boolean getAnswer() {
			return false;
		}
	}
	List<Sound> correctSounds; // the list contains all the sounds, assume each sound is unique
	Sound errorSound;
	Sound prev;
	User user = new User();
	
	public void playSound() {
		boolean correct = user.getAnswer();
		if(!correct) {
			play(errorSound);
			if(prev != null) {
				correctSounds.add(prev);
				prev = null;
			}
		} else {
			Random random = new Random(correctSounds.size());
			int next = random.nextInt();
			Sound curr = correctSounds.get(next);
			play(curr);
			correctSounds.remove(curr);
			if(prev != null) {
				correctSounds.add(prev);
			}
			prev = curr;
		}
	}

	private void play(Sound errorSound2) {
		// TODO Auto-generated method stub
		
	}
}
