package priam.data.priamdataservice.services;

import org.springframework.stereotype.Service;
import priam.data.priamdataservice.dto.*;
import priam.data.priamdataservice.entities.DataUsage;
import priam.data.priamdataservice.entities.Processing;
import priam.data.priamdataservice.mappers.ProcessingMapper;
import priam.data.priamdataservice.openfeign.DataSubjectRestClient;
import priam.data.priamdataservice.repositories.ProcessingRepository;

import javax.annotation.Generated;
import javax.transaction.Transactional;
import java.util.*;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-05-23T23:03:41+0530"
)
@Transactional
@Service
public class ProcessingService implements ProcessingServiceInterface  {


    private ProcessingMapper processingMapper;
    private DataUsageService dataUsageService;
    private DataServiceImpl dataService;
    private ProcessingRepository processingRepository;
    private DataSubjectRestClient dataSubjectRestClient;

    public ProcessingService(ProcessingMapper processingMapper, ProcessingRepository processingRepository, DataUsageService dataUsageService, DataServiceImpl dataService, DataSubjectRestClient dataSubjectRestClient) {
        this.processingMapper = processingMapper;
        this.processingRepository = processingRepository;
        this.dataUsageService = dataUsageService;
        this.dataService = dataService;
        this.dataSubjectRestClient = dataSubjectRestClient;
    }
    @Override
    public Processing createProcessing(ProcessingRequestDTO processingRequestDTO) {
        Processing processing = processingMapper.fromProcessingDTO(processingRequestDTO);
        Processing res = processingRepository.save(processing);
        return res;
    }

    @Override
    public ProcessingResponseDTO updateProcessing(Long id,ProcessingRequestDTO processingRequestDTO) {
        //log.info("UpdateProcessing start Process !");
        Processing processing = processingMapper.fromProcessingDTO(processingRequestDTO);
        Processing oldProcessing = processingRepository.findById(id).get();
        oldProcessing.setCategory(processing.getCategory());
        oldProcessing.setCreationDate(processing.getCreationDate());
        oldProcessing.setDataUsages(processing.getDataUsages());
        oldProcessing.setMesures(processing.getMesures());
        oldProcessing.setName(processing.getName());
        oldProcessing.setPurposes(processing.getPurposes());
        oldProcessing.setType(processing.getType());
        oldProcessing.setUpdatingDate(new Date());
        processingRepository.save(oldProcessing);
        return processingMapper.fromProcessing(oldProcessing);
    }

    @Override
    public boolean deleteProcessing(Long processingId) {
        processingRepository.deleteById(processingId);
        return true;
    }

    @Override
    public ProcessingResponseDTO getProcessing(Long processingId) {
        Processing processing = processingRepository.findById(processingId).get();
        //processing.setDataUsages((List<DataUsage>)dataUsageService.getDataUsages(processingId));
        return processingMapper.fromProcessing(processing);
    }

    @Override
    public Collection<Processing> getProcessings() {
        Collection<Processing> processings = processingRepository.findAll();
        for (Processing processing: processings){
            processing.setDataUsages((List<DataUsage>)dataUsageService.getDataUsages(processing.getId()));
        }
        return processings;
        //return processingRepository.findAll();
    }

    @Override
    public Collection<ProcessingResponseDTO> getProcessingsByDsc(int dscId) {
        Collection<Processing> processings = getProcessings();
        List<Integer> personalDataId = new LinkedList<>();
        Collection<ProcessingResponseDTO> processingsDsc = new LinkedList<>();

        for (DataResponseDTO data: dataService.findAllDataByDataSubjectCategory(dscId)) {
            personalDataId.add(data.getId());
        }

        for (Processing processing: processings) {
            int cpt = 0;
            for (DataUsage dataUsage: processing.getDataUsages()) {
                System.out.println("donnée personnelles "+ personalDataId );
                System.out.println("donnée personnelles du processing "+ dataUsage.getDataId() );
                if (personalDataId.contains(dataUsage.getDataId())) {
                    cpt ++;
                    break;
                }
            }

            if(cpt != 0)
                processingsDsc.add(processingMapper.fromProcessing(processing));
        }

        return processingsDsc;
    }
    @Override
    public List<ProcessingPersonalDataDTO> getProcessingPersonalDataListPurposes(String idRef) {
        // Retrieve the DataSubject to have its category
        DataSubjectResponseDTO dataSubject = dataSubjectRestClient.getDataSubjectByRef(idRef);
        // Retrieve associated processings and datas
        Collection<ProcessingResponseDTO> processings = this.getProcessingsByDsc(dataSubject.getDscId());
        ArrayList<DataResponseDTO> datas = new ArrayList<>(dataService.findAllDataByDataSubjectCategory(dataSubject.getDscId()));

        // Construct response
        ArrayList<ProcessingPersonalDataDTO> response = new ArrayList<>();
        for (ProcessingResponseDTO processing: processings) {
            ProcessingPersonalDataDTO p = new ProcessingPersonalDataDTO();
            p.setProcessingName(processing.getName());
            // Purposes
            processing.getPurposes().forEach(purpose -> {
                p.addPurposeDescription(purpose.getDescription());
            });
            // Datas
            processing.getDataUsages().forEach(dataUsage -> {
                p.addDataAttribute(dataService.getAttributeById(dataUsage.getDataId()));
            });

            response.add(p);
        }

        return response;
    }

}