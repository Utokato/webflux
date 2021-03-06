package cn.utokato.webflux03.controller;

import cn.utokato.webflux03.domain.MyEvent;
import cn.utokato.webflux03.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author lma
 * @date 2019/09/05
 */
@RestController
@RequestMapping("/events")
public class EventController {

    private final EventRepository eventRepository;

    @Autowired
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<Void> loadEvents(@RequestBody Flux<MyEvent> events) {
        return eventRepository.insert(events).then();
    }

    /**
     * 通过浏览器获取 mongodb 中最新的一条数据。
     * 以 application/stream+json 的方式进行返回
     * SSE Serve-sent Event
     *
     * @return
     */
    @GetMapping(path = "", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<MyEvent> getEvents() {
        return eventRepository.findBy();
    }
}
