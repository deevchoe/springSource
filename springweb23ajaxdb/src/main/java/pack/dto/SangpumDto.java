package pack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pack.entity.Sangpum;

@Getter
@Builder		// toDto 만들어야 하니까..
@AllArgsConstructor
@NoArgsConstructor
public class SangpumDto {
	private int code;
	private String sang, su, dan;
	
	public static SangpumDto toDto(Sangpum sangpum) {	// 엔티티가 들어올거야
		return SangpumDto.builder()
				.code(sangpum.getCode())
				.sang(sangpum.getSang())
				.su(sangpum.getSu())
				.dan(sangpum.getDan())
				.build();
	}
}
