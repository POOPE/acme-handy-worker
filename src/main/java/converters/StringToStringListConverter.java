
package converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Transactional
@Component
public class StringToStringListConverter implements Converter<String, List<String>> {

	@Override
	public List<String> convert(String text) {
		List<String> res = new ArrayList<>();

		try {
			if (StringUtils.isEmpty(text)) {
				//do nothing
			} else {
				String ids[] = text.split("&&");
				for (int i = 0; i < ids.length; i++) {
					res.add(ids[i].trim());
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		return res;
	}

}
