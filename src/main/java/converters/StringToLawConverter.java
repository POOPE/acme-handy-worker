
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import services.LawService;
import domain.Law;

@Transactional
@Component
public class StringToLawConverter implements Converter<String, Law> {

	@Autowired
	private LawService	lawService;


	@Override
	public Law convert(String text) {
		Law res;

		Law law = this.lawService.findById(Integer.valueOf(text.replace(",", "").trim()));
		res = law;

		return res;
	}

}
