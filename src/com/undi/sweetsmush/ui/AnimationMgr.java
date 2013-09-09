package com.undi.sweetsmush.ui;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AnimationMgr {
	private static AnimationMgr _instance;
	
	private List<Animation> anims;
	private List<Animation> pendingCallbacks;
	
	private AnimationMgr(){
		anims = new LinkedList<Animation>();
		pendingCallbacks = new LinkedList<Animation>();
	}
	
	public synchronized static AnimationMgr getInstance(){
		if(_instance == null){
			_instance = new AnimationMgr();
		}
		return _instance; 
	}
	
	public void addAnimation(Animation a){
		anims.add(a);
	}
	
	public boolean anyGameStopping(){
		for(Animation anim: anims){
			if(anim.isGameStopping()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Updates all of the animations.
	 * If any are finished, clears them out of the active list, and
	 *   calls their callback
	 */
	public void updateAll(){
		long curTime = System.currentTimeMillis();
		Iterator<Animation> iter = anims.iterator();
		while(iter.hasNext()){
			Animation cur = iter.next();
			cur.update(curTime);
			if(cur.isDone()){
				pendingCallbacks.add(cur);
				iter.remove();
			}
		}
		iter = pendingCallbacks.iterator();
		while(iter.hasNext()){
			Animation cur = iter.next();
			cur.doCallback();
			iter.remove();
		}
	}
}
