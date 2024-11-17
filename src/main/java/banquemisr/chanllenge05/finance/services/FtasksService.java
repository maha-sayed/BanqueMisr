package banquemisr.chanllenge05.finance.services;

import banquemisr.chanllenge05.finance.entities.FtasksEntity;
import banquemisr.chanllenge05.finance.entities.FtasksHisEntity;
import banquemisr.chanllenge05.finance.repositories.FtasksHisRepo;
import banquemisr.chanllenge05.finance.repositories.FtasksRepo;
import banquemisr.chanllenge05.finance.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.*;

import org.springframework.beans.*;

@Service
public class FtasksService implements businessInterface {
    @Autowired
    ObjectMapper objMapper;
    @Autowired
    RequestData requestData;
    @Autowired
    FtasksRepo ftasksRepo;
    @Autowired
    FtasksHisRepo ftaskHisRepo;
    @Autowired
    private AppUtils appUtils;
    @Override
    public boolean validateItems(JsonRequest request) {
        for (Object object : request.getObjectdata()) {
            FtasksVo ftask = objMapper.convertValue(object, FtasksVo.class);
            if(request.getMetadata().getFuncMode().equals("U")){
                if(!appUtils.isNotNullValidator(ftask.getStatus())){
                    requestData.setMessageCode(1);
                    requestData.setMessageType(2);
                    requestData.setMessageDescr("Task status cannot be null");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean doInsert(JsonRequest request) {
        for (Object object : request.getObjectdata()) {
            FtasksVo ftask = objMapper.convertValue(object, FtasksVo.class);
            FtasksEntity ftasksEntity = new FtasksEntity();
            BeanUtils.copyProperties(ftask , ftasksEntity);
            FtasksHisEntity ftaskHis= new FtasksHisEntity();
            BeanUtils.copyProperties(ftasksEntity , ftaskHis);


            ftasksRepo.save(ftasksEntity);
            ftaskHisRepo.save(ftaskHis);
            requestData.setMessageDescr(ftask.getTaskno().toString());
        }
        return true;
    }

    @Override
    public boolean doUpdate(JsonRequest request) {
        for (Object object : request.getObjectdata()) {
            FtasksVo ftask = objMapper.convertValue(object, FtasksVo.class);
            Optional<FtasksEntity> ftasksEntity = ftasksRepo.findById(ftask.getTaskno());
            if(!ftasksEntity.isEmpty()) {
                FtasksHisEntity ftaskHis= new FtasksHisEntity();
                ftaskHis.setTaskno(ftask.getTaskno());
                ftaskHis.setTitle(ftask.getTitle());
                ftaskHis.setDescription(ftask.getDescription());
                ftaskHis.setOldStatus(ftasksEntity.get().getStatus());
                ftaskHis.setNewStatus(ftask.getStatus());
                ftaskHis.setDueDate(ftask.getDueDate());
                ftaskHis.setPriority(ftask.getPriority());
                ftaskHis.setUpdatedDate(new Date());
                ftaskHisRepo.save(ftaskHis);
                ftasksEntity.get().setStatus(ftask.getStatus());
                ftasksEntity.get().setDueDate(ftask.getDueDate());
                ftasksRepo.save(ftasksEntity.get());

            }
        }
        return true;
    }

    @Override
    public boolean doDelete(JsonRequest request) {
        for (Object object : request.getObjectdata()) {
            FtasksVo ftask = objMapper.convertValue(object, FtasksVo.class);
            Optional<FtasksEntity> ftasksEntity = ftasksRepo.findById(ftask.getTaskno());
            if(!ftasksEntity.isEmpty()) {
                ftasksRepo.delete(ftasksEntity.get());
            }
        }
        return true;
    }
    public Response calculateObject(JsonRequest request) {
        Response response = new Response();
        LinkedList<Object> details = new LinkedList<Object>();

        for (Object object : request.getObjectdata()) {

            if (appUtils.isNotNullValidator(request.getMetadata().getCalcObject())) {

                for (String str : request.getMetadata().getCalcObject()) {

                    if (str.equalsIgnoreCase("taskInfo")) {
                        FtasksVo ftask = objMapper.convertValue(object, FtasksVo.class);
                        Optional<FtasksEntity> ftaskEntity = ftasksRepo.findById(ftask.getTaskno());
                        if (!ftaskEntity.isEmpty()) {
                            details.add(ftaskEntity.get());
                            response.setObjectdata(details);
                        }
                    }
                    if (str.equalsIgnoreCase("pageTasks")){
                        PaginationVo padinationVo = objMapper.convertValue(object,PaginationVo.class);
                        Pageable pageData = (Pageable) PageRequest.of(padinationVo.getPageId(),padinationVo.getPaseSize());
                        Page<FtasksEntity> ftasksList = ftasksRepo.findAll(pageData);
                        details.add(ftasksList);
                        response.setObjectdata(details);
                    }
                    if (str.equalsIgnoreCase("tasksByStatus")) {
                        FtasksVo ftask = objMapper.convertValue(object, FtasksVo.class);
                        List<FtasksEntity> ftaskEntity = ftasksRepo.findByStatus(ftask.getStatus());
                        if (!ftaskEntity.isEmpty()) {
                            details.add(ftaskEntity);
                            response.setObjectdata(details);
                        }
                    }
                    if (str.equalsIgnoreCase("tasksByDueDate")) {
                        FtasksVo ftask = objMapper.convertValue(object, FtasksVo.class);
                        List<FtasksEntity> ftaskEntity = ftasksRepo.findByDueDate(ftask.getDueDate());
                        if (!ftaskEntity.isEmpty()) {
                            details.add(ftaskEntity);
                            response.setObjectdata(details);
                        }
                    }
                    if (str.equalsIgnoreCase("statusLkp")) {
                        LinkedList<HashMap<Integer,String>> stsLkp = new LinkedList<>();
                        HashMap<Integer,String> sts = new HashMap<>();
                        sts.put(1,"TODO");
                        sts.put(2,"InProgress");
                        sts.put(3,"Done");
                        stsLkp.add(sts);
                        details.add(stsLkp);
                        response.setObjectdata(details);

                    }
                }
            }
        }
            return response;
        }


}
