
package converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import services.LawService;
import domain.Law;

@Transactional
@Component
public class StringToLawListConverter implements Converter<String, List<Law>> {

	@Autowired
	private LawService	lawService;


	@Override
	public List<Law> convert(String text) {
		List<Law> res = new ArrayList<>();

		try {
			if (StringUtils.isEmpty(text)) {
				//do nothing
			} else {
				String ids[] = text.split(",");
				for (int i = 0; i < ids.length; i++) {
					Law law = this.lawService.findById(Integer.valueOf(ids[i].trim()));
					res.add(law);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

		return res;
	}

}
