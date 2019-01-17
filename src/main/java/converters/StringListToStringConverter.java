
package converters;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional
@Component
public class StringListToStringConverter implements Converter<List<String>, String> {

	@Override
	public String convert(List<String> list) {
		String res = "";

		if (list == null || list.isEmpty()) {
			res = null;
		} else {
			Assert.isInstanceOf(String.class, list.get(0), "Object is not of type String");
			for (int i = 0; i < list.size(); i++) {
				res = res + list.get(i);
				if (i < list.size() - 1) {
					res = res + "&&";
				}
			}
		}

		return res;
	}

}
