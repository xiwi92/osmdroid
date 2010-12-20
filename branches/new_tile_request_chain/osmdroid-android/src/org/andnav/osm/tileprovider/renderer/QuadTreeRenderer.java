package org.andnav.osm.tileprovider.renderer;

import org.andnav.osm.ResourceProxy.string;
import org.andnav.osm.tileprovider.OpenStreetMapTile;

class QuadTreeRenderer extends OpenStreetMapOnlineTileRendererBase {

	QuadTreeRenderer(String aName, string aResourceId, int aZoomMinLevel,
			int aZoomMaxLevel, int aTileSizePixels,
			String aImageFilenameEnding, String... aBaseUrl) {
		super(aName, aResourceId, aZoomMinLevel, aZoomMaxLevel,
				aTileSizePixels, aImageFilenameEnding, aBaseUrl);
	}

	@Override
	public String getTileURLString(OpenStreetMapTile aTile) {
		return getBaseUrl() + quadTree(aTile) + mImageFilenameEnding;
	}

	/**
	 * Converts TMS tile coordinates to QuadTree
	 * 
	 * @param aTile
	 *            The tile coordinates to convert
	 * @return The QuadTree as String.
	 */
	private String quadTree(final OpenStreetMapTile aTile) {
		final StringBuilder quadKey = new StringBuilder();
		for (int i = aTile.getZoomLevel(); i > 0; i--) {
			int digit = 0;
			int mask = 1 << (i - 1);
			if ((aTile.getX() & mask) != 0)
				digit += 1;
			if ((aTile.getY() & mask) != 0)
				digit += 2;
			quadKey.append("" + digit);
		}

		return quadKey.toString();
	}

}
