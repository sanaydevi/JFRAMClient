package client;

import java.util.HashMap;

/**
 * Returning the lowest value in a given channel
 *
 * @author Team 2
 * @version 1.0
 */
public class ClientLowestStats implements StatsInterface {
    private HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();

	@Override
	public void onReceiveData(int channel, int data) {
        int cur = getStats(channel);
        cur = Math.min(cur, data);
        hash.put(channel, cur);
	}

	@Override
	public int getStats(int channel) {
		if (!hash.containsKey(channel)) {
            hash.put(channel, Integer.MAX_VALUE);
        }
        return hash.get(channel);
	}
}