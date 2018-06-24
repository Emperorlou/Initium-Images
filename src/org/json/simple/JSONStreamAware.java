package org.json.simple;

import java.io.IOException;
import java.io.Writer;


/**
 * Beans that support customized output of JSON text to a writer shall implement this interface.
 * @author FangYidong<fangyidong@yahoo.com.cn>
 */
@GwtIncompatible
public interface JSONStreamAware {
	/**
	 * write JSON string to out using indent
	 */
	void writeJSONString(Writer out, String indent) throws IOException;
}
