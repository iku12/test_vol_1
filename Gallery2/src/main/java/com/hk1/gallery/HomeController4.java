package com.hk1.gallery;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import com.hk1.gallery.dto.DabgeulDto;
import com.hk1.gallery.dto.GalleryDto;
import com.hk1.gallery.service.IDabgeulService;
import com.hk1.gallery.service.IGalleryService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController4 {

	@Autowired
	private IGalleryService galleryService;
	
	
	@Autowired
	private IDabgeulService dabgeulService;


	private static final Logger logger = LoggerFactory.getLogger(HomeController4.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	/*갤러리 리스트 뽑기*/
	
	@RequestMapping(value = "/gallerylist.do", method = RequestMethod.GET)
	public String gallerylist(Locale locale, Model model,HttpSession session) {
		logger.info("gallerylist 가자 {}.", locale);

		List<GalleryDto> list = galleryService.selectGalleryList();

		model.addAttribute("list",list);
		
		//최초 답글 리스트 뽑기
/*		List<DabgeulDto> Dlist = dabgeulService.selectDabgeulList();
		
		model.addAttribute("Dlist",Dlist);*/
		
		
		return "gallerylist";
	}
	
	//내 갤러리 볼때 mno로 볼때
	
	@RequestMapping(value = "/selectM_noGalleryList.do", method = RequestMethod.GET)
	public String selectM_noGalleryList(Locale locale, Model model) {
		logger.info("selectM_noGalleryList 가자 {}.", locale);

		//세션에 m_no =2 이 담길것 임시 
		
		
		GalleryDto Ddto = new GalleryDto();
		int m_no =2;
		Ddto.setM_no(m_no);
		
		// 임시값 끝
		
		List<GalleryDto> list = galleryService.selectM_noGalleryList(m_no);
		

		model.addAttribute("list",list);


		return "gallerylist";
	}
	
	
	/*스테이트 3인 갤러리만 불러오자*/
	@RequestMapping(value = "/selectG_stateGalleryList.do", method = RequestMethod.GET)
	public String selectG_stateGalleryList(Locale locale, Model model,HttpSession session) {
		logger.info("selectG_stateGalleryList 가자 {}.", locale);

	/*	세션에 g_state =3 이 담길것 임시 */
		int g_state =3;
		
		List<GalleryDto> list = galleryService.selectG_stateGalleryList(g_state);

		model.addAttribute("list",list);
		
		

		return "gallerylist";
	}	
	



	@RequestMapping(value = "/insertGalleryForm.do", method = RequestMethod.GET)
	public String insertGalleryForm(Locale locale, Model model) {
		logger.info("insertGalleryForm 가자 {}.", locale);

		return "insertGalleryForm";

	}


	@RequestMapping(value = "/insertGallery.do", method = RequestMethod.POST)
	public String insertGallery(Locale locale, Model model, MultipartHttpServletRequest mtfRequest)  {
		logger.info("insertGallery 가자 {}.", locale);

		GalleryDto galleryDto = new GalleryDto();
		System.out.println("dto = ?" + galleryDto);
		
		String g_name = mtfRequest.getParameter("g_name");
		String g_intro = mtfRequest.getParameter("g_intro");
		String g_adress = mtfRequest.getParameter("g_adress");
		String g_tel = mtfRequest.getParameter("g_tel");
		
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
		
		
		//파일저장 경로
		String path = "C:/Users/hk-edu/git/gallery/Gallery/src/main/webapp/upload/";


	/*	for (MultipartFile multifile : fileList) {
			
			//요청한 파일에서 원본 파일명 구하기
			String orign_fname = multifile.getOriginalFilename();
			
			String saveDirectory = path + orign_fname;
			
			try {
				multifile.transferTo(new File(saveDirectory));//파일객체의 경로대로 업로드 실행
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		
		
		for (int i = 0; i < fileList.size(); i++) {
			
			String imgdbname= fileList.get(i).getOriginalFilename();
			
			String stored_fname= createUUId()
					+(imgdbname.substring(imgdbname.lastIndexOf(".")));

			String saveDirectory = path + stored_fname;
			
			try {
				
				fileList.get(i).transferTo(new File(saveDirectory));//파일객체의 경로대로 업로드 실행
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			
			if (i==0) {
				galleryDto.setG_img1(stored_fname);
			}else if (i==1) {
				galleryDto.setG_img2(stored_fname);
			}else if (i==2) {
				galleryDto.setG_img3(stored_fname);
			}else if (i==3) {
				galleryDto.setG_img4(stored_fname);
			}
		}
		
		galleryDto.setG_name(g_name);
		galleryDto.setG_intro(g_intro);
		galleryDto.setG_adress(g_adress);
		galleryDto.setG_tel(g_tel);
		
	
		boolean isS = galleryService.insertGallery(galleryDto);

		if(isS) {
			return "redirect:gallerylist.do";
		}else {
			logger.info("파일 업로드 실패");
			return "error";
		}
		
		

	}

	
	//갤러리 상세
	@RequestMapping(value = "/selectGallery.do", method = RequestMethod.GET)
	public String selectGallery(Locale locale, Model model, int g_no)  {
		logger.info("selectGallery 가자 {}.", locale);

		GalleryDto galleryDto = galleryService.selectGallery(g_no);

		model.addAttribute("galleryDto",galleryDto);

		System.out.println(galleryDto);


		return "selectGallery";
	}





	@RequestMapping(value = "/updateGallery.do", method = RequestMethod.POST)
	public String updateGallery(Locale locale, Model model,  MultipartHttpServletRequest mtfRequest)  {
		logger.info("updateGallery 가자 {}.", locale);
	
		GalleryDto galleryDto = new GalleryDto();
		
		String g_name = mtfRequest.getParameter("g_name");
		String g_intro = mtfRequest.getParameter("g_intro");
		String g_adress = mtfRequest.getParameter("g_adress");
		String g_no = mtfRequest.getParameter("g_no");
		String m_no = mtfRequest.getParameter("m_no");
		String g_state  = mtfRequest.getParameter("g_state");
		String g_tel  = mtfRequest.getParameter("g_tel");
		
		galleryDto.setG_name(g_name);
		galleryDto.setG_intro(g_intro);
		galleryDto.setG_adress(g_adress);
		galleryDto.setG_no(Integer.parseInt(g_no));
		galleryDto.setM_no(Integer.parseInt(m_no));
		galleryDto.setG_state(Integer.parseInt(g_state));
		galleryDto.setG_tel(g_tel);
		
		System.out.println(galleryDto.toString());
		
		
		
		
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
		
		
		//파일저장 경로
		String path = "C:/Users/hk-edu/git/gallery/Gallery/src/main/webapp/upload/";

		
		for (int i = 0; i < fileList.size(); i++) {
			
			String imgdbname= fileList.get(i).getOriginalFilename();
			
			String stored_fname= createUUId()
					+(imgdbname.substring(imgdbname.lastIndexOf(".")));

			String saveDirectory = path + stored_fname;
			
			try {
				
				fileList.get(i).transferTo(new File(saveDirectory));//파일객체의 경로대로 업로드 실행
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			
			if (i==0) {
				galleryDto.setG_img1(stored_fname);
			}else if (i==1) {
				galleryDto.setG_img2(stored_fname);
			}else if (i==2) {
				galleryDto.setG_img3(stored_fname);
			}else if (i==3) {
				galleryDto.setG_img4(stored_fname);
			}
		}
		
		boolean isS = galleryService.updateGallery(galleryDto);

		if(isS) {
			return "redirect:gallerylist.do";
		}else {
			logger.info("파일 업로드 실패");
			return "error";
		}

	}
	
	@RequestMapping(value = "/deleteGallery.do", method = RequestMethod.GET)
	public String deleteGallery(Locale locale, Model model, GalleryDto galleryDto, int g_no)  {
		logger.info("deleteGallery 가자 {}.", locale);
		

		boolean isS = galleryService.deleteGallery(g_no);

		if(isS) {
			return "redirect:gallerylist.do";
		}else {
			logger.info("파일 업로드 실패");
			return "error";
		}

	}
	
	
	
	
	
	//답글을 달아보자 ////
	

	//답글 쓰기 관련
/*	@ResponseBody
	@RequestMapping(value = "/DabgeulInsertAjax.do", method = RequestMethod.POST)
	public Map<String, List<DabgeulDto>> DabgeulInsertAjax(Locale locale, Model model, int e_no, String d_content, int m_no, String m_name)  {
		logger.info("DabgeulInsertAjax 가자 {}.", locale);
	
		System.out.println(e_no);
		
		System.out.println(d_content);
		System.out.println(m_no);
		System.out.println(e_no);

		
		DabgeulDto dto = new DabgeulDto();
		
		dto.setE_no(e_no);
		dto.setD_content(d_content);
		dto.setM_no(m_no);
		dto.setM_name(m_name);


		boolean isS =  dabgeulService.InsertDabgeul(dto);

//		if(isS) {
			List<DabgeulDto> Dlist = dabgeulService.selectDabgeulList();
			Map<String, List<DabgeulDto>> map=new HashMap<String, List<DabgeulDto>>();
			map.put("dlist", Dlist);
			
			return map;
			
//		}else {
//			logger.info("파일 업로드 실패");
//			Map<String, String>map=new HashMap<String, String>();
//			map.put("dlist", "파일 업로드 실패");
//			return map;
//		
//		}
		
	}*/
	
	
	@ResponseBody
	@RequestMapping(value = "/DabgeulInsertAjax.do", method = RequestMethod.POST)
	public String DabgeulInsertAjax(Locale locale, Model model, int e_no, String d_content, int m_no, String m_name)  {
		logger.info("DabgeulInsertAjax 가자 {}.", locale);
	
		System.out.println(e_no);
		
		System.out.println(d_content);
		System.out.println(m_no);
		System.out.println(e_no);
		
		
	/*	session.removeAttribute("Dlist");
		
		List<DabgeulDto> Dlist = dabgeulService.selectDabgeulList();
		
		session.setAttribute("Dlist",Dlist);*/
		
		DabgeulDto dto = new DabgeulDto();
		
		dto.setE_no(e_no);
		dto.setD_content(d_content);
		dto.setM_no(m_no);
		dto.setM_name(m_name);


		boolean isS =  dabgeulService.InsertDabgeul(dto);

		if(isS) {

			
			return "y";
			
		}else {
			logger.info("파일 업로드 실패");

			return "n";
		
		}
		
	}
	

	
	@RequestMapping(value = "/DabgeulList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String DabgeulLisㅅ(Locale locale, Model model,HttpSession session)  {
		logger.info("DabgeulList 가자 {}.", locale);
		
		//로그인 세션 값  
		DabgeulDto Ddto = new DabgeulDto();
		int m_no =0;
		int e_no=0;
		Ddto.setE_no(e_no);
		Ddto.setM_no(m_no);
		session.setAttribute("Ddto",Ddto);
		
		List<DabgeulDto> Dlist = dabgeulService.selectDabgeulList();
		
		model.addAttribute("Dlist",Dlist);
	
		
		return "dabgeul";
		
		
		
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//랜덤한 값 32자리 만드는 메서드 
	public String createUUId() {
		return UUID.randomUUID().toString().replaceAll("-","");
	}
	
}










